package dao;

import Utils.HibernateUtils;
import Utils.RedisUtil;
import domain.HotelHome;
import domain.User;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
//redis缓存,将所有方法加上synchronized ,确保数据一致
//将外键为6的房间定位无人住的房间，应为redis不能存null
public class HotelRedisDao {
    //这里就存房间数
    //数据只会刷到hotelHome
    private UserDao userDao=new UserDao();

//    static {
//        Session session = HibernateUtils.geTCurrentSession();
//        Jedis jedis = RedisUtil.getJedis();
//        Transaction transaction = session.beginTransaction();
//        try {
//            Query query = session.createQuery("from  HotelHome ");
//            List list = query.list();
//            Object[] objects = list.toArray();
//            HotelHome hotelHome = null;
//            Map<String, String> hotelHomeMap = null;
//            for (Object Hotel : objects) {
//                hotelHome = (HotelHome) Hotel;//房间
//                Long name = hotelHome.getName();
//                String prices = hotelHome.getPrices();//价格
//                User user = hotelHome.getUser();//用户
//                switch (prices) {//将不同价位的对象id放在一个list里
//                    case "5000":
//                        jedis.sadd("maxHotel", String.valueOf(name));
//                        break;
//                    case "1000":
//                        jedis.sadd("mediumHotel", String.valueOf(name));
//                        break;
//                    case "500":
//                        jedis.sadd("smallHotel", String.valueOf(name));
//                        break;
//                }
//
//                toRedisMap(hotelHome);
//            }
//        } catch (Exception e) {
//            //什么都不做
//            e.printStackTrace();
//        } finally {
//            transaction.commit();
//            jedis.close();
//        }
//    }
    public synchronized void setRedisHome() {
        Session session = HibernateUtils.geTCurrentSession();
        Jedis jedis = RedisUtil.getJedis();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("from  HotelHome ");
            List list = query.list();
            Object[] objects = list.toArray();
            HotelHome hotelHome = null;
            Map<String, String> hotelHomeMap = null;
            for (Object Hotel : objects) {
                hotelHome = (HotelHome) Hotel;//房间
                Long name = hotelHome.getName();
                String prices = hotelHome.getPrices();//价格
                User user = hotelHome.getUser();//用户
                switch (prices) {//将不同价位的对象id放在一个list里
                    case "5000":
                        jedis.sadd("maxHotel", String.valueOf(name));
                        break;
                    case "1000":
                        jedis.sadd("mediumHotel", String.valueOf(name));
                        break;
                    case "500":
                        jedis.sadd("smallHotel", String.valueOf(name));
                        break;
                }
                toRedisMap(hotelHome);
            }
        } catch (Exception e) {
            //什么都不做
            e.printStackTrace();
        } finally {
            jedis.close();
            transaction.commit();

        }
    }


    //先将所有的home对象存在redis中
    //一个set集合


    //将reids数据刷到mysql中
    //将hotel有居住人的人，刷回mysql中
    public synchronized void redisToMysql() {
        ArrayList<String> nameList = getNameList();//获取
        ArrayList<HotelHome> hotelHomes = getHotelHomes(nameList);
        for(HotelHome hotelHome:hotelHomes){
            updateHotelHome(hotelHome);
        }
    }

    //以上2个操作，用户无法操作，以上时redis和mysql数据同步操作


    //得到一个redis对象   查一个
    public  synchronized  HotelHome getHotelHome(String name){
        Jedis jedis = RedisUtil.getJedis();
        Map<String, String> nameArray = jedis.hgetAll(name);
        String user = nameArray.get("user");
        User bigUser = userStringToUser(user);//将字符串变成user
        String HotelName = nameArray.get("name");//房间名
        String prices = nameArray.get("prices");//价格
        String volume = nameArray.get("volume");//容量
       HotelHome hotelHome=new HotelHome(Long.valueOf(HotelName),prices,Long.valueOf(volume));
        hotelHome.setUser(bigUser);//将user设置进去
        jedis.close();
        return hotelHome;
    }
    //得到所有的redis中hotel对象   查全部
    public synchronized ArrayList<HotelHome> getHotelHomes(ArrayList<String> nameList) {
        ArrayList<HotelHome> hotelHomes=new ArrayList<>();
        Jedis jedis = RedisUtil.getJedis();
        HotelHome hotelHome = null;
        for(String name:nameList){
            try {
                 hotelHome = getHotelHome(name);
                hotelHomes.add(hotelHome);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                jedis.close();
            }
        }
        return hotelHomes;
    }

    //向msql中添加hotelHOme
    public synchronized void addHotelHome(HotelHome hotelHome){
        Session session = HibernateUtils.geTCurrentSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(hotelHome);
        }catch (Exception e){
            throw  new RuntimeException("添加失败");
        }finally {
            if(transaction!=null){
                try {
                    transaction.commit();
                }catch (Exception e){
                }
            }
            session.close();
        }
    }



    //更新hotelhome
    public synchronized void updateHotelHome(HotelHome hotelHome){
        Session session = HibernateUtils.geTCurrentSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.update(hotelHome);//执行更新操作

        }catch (Exception e){
            throw  new RuntimeException("更新失败");
        }finally {
            if(transaction!=null){
                try {
                    transaction.commit();
                }catch (Exception e){
                }
            }
            session.close();
        }
    }



    //将user字符串转化成user对象
    private synchronized User  userStringToUser(String userString){

        //这里最好的方法就是分割字符串
        /**
         * User{id=1, name='叶仕昊',
         * password='6732410a', gender='man',
         * birthday='2000-10-01', address='安徽省桐城市',
         * phoneNumber='18133016308'}
         */
        String[] split = userString.split(",");
        //第一字符串
        String userid=  split[0].substring(split[0].indexOf("id"),split[0].length());
        String id = userid.substring(userid.indexOf("=")+1,userid.length());

        //第二个字符串
        String username=  split[1].substring(split[1].indexOf("name"),split[1].length());
        String uname = username.substring(username.indexOf("=")+1,username.length()).replace("\'","");


        //第三个字符串
        String Userpassword=  split[2].substring(split[2].indexOf("password"),split[2].length());
        String passowrd = Userpassword.substring(Userpassword.indexOf("=")+1,Userpassword.length()).replace("\'","");

        //第四个字符串
        String Usergender=  split[3].substring(split[3].indexOf("gender"),split[3].length());
        String gender = Usergender.substring(Usergender.indexOf("=")+1,Usergender.length()).replace("\'","");


        //第四个字符串
        String Userbirthday=  split[4].substring(split[4].indexOf("birthday"),split[4].length());
        String birthday = Userbirthday.substring(Userbirthday.indexOf("=")+1,Userbirthday.length()).replace("\'","");


        //第五个字符串
        String Useraddress=  split[5].substring(split[5].indexOf("address"),split[5].length());
        String address = Useraddress.substring(Useraddress.indexOf("=")+1,Useraddress.length()).replace("\'","");


        //第六个字符串
        String UserphoneNumber=  split[6].substring(split[6].indexOf("phoneNumber"),split[6].length());
        String phoneNumber = UserphoneNumber.substring(UserphoneNumber.indexOf("=")+1,UserphoneNumber.length()).replace("\'","").replace("}","");

        User bigUser=new User(uname,passowrd,gender,birthday,address,phoneNumber);
        bigUser.setId(Long.valueOf(id));
       return bigUser;//这是获取好的user类
    }
    //读取个不同准类房，并且放回一个集合
    public synchronized ArrayList<String> getNameList() {
        Jedis jedis = RedisUtil.getJedis();
        Object[] maxHotel = jedis.smembers("maxHotel").toArray();
        Object[] mediumHotel = jedis.smembers("mediumHotel").toArray();
        Object[] smallHotel = jedis.smembers("smallHotel").toArray();
        jedis.close();
        //这是我们固定存储的地方
        return getNameList(maxHotel, mediumHotel, smallHotel);
    }
    //回写hotelHome对象,更新房的对象，也就是更新了用户，用户也确定了房
    public static synchronized void toRedisMap(HotelHome hotelHome) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Jedis jedis = RedisUtil.getJedis();
        Map<String, String> hotelHomeMap;
        hotelHomeMap = BeanUtils.describe(hotelHome);//将对象转为map
        //这里建bean对象转化成map；
        jedis.hmset(String.valueOf(hotelHome.getName()), hotelHomeMap);//将对象存到redis中
        //在redis上直接存user对象，并且不再显示user不为null的map
        //同时再list里去掉那个user的id
        jedis.close();
    }

    private synchronized ArrayList<String> getNameList(Object[] maxHotel, Object[] mediumHotel, Object[] smallHotel) {
        ArrayList<String> nameList = new ArrayList<>();
        for (Object name : maxHotel) {
            nameList.add((String) name);
        }
        for (Object name : mediumHotel) {
            nameList.add((String) name);
        }
        for (Object name : smallHotel) {
            nameList.add((String) name);
        }
        return nameList;
    }

    public synchronized static <T> T toBean(Map map,Class<T> tClass){
        try {
            T bean =tClass.newInstance();
            BeanUtils.populate(bean,map);
            return bean;
        } catch (Exception e) {
           throw  new RuntimeException(e);
        }
    }
}
