package dao;

import Utils.HibernateUtils;
import domain.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import javax.security.auth.login.LoginException;
import java.util.List;

public class UserDao {
    public void regist(User usr) throws LoginException {
        Session session = HibernateUtils.geTCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("from User where name=:name");//重名检查
            query.setParameter("name", usr.getName());
            List list = query.list();
            if (list.size() == 0) {
                session.save(usr);//没有就进行注册
            } else {
                throw new LoginException("注册失败");
            }
            //注册操作
        }catch (Exception e){
            transaction.rollback();
            throw new LoginException(e.toString());
        }finally {
            if(transaction!=null){
                transaction.commit();
            }
            session.close();
        }
    }


    public  User login(User user) throws LoginException {
        Session session = null;
        Transaction transaction = null;
        User retrunUser=null;
        try{
            session = HibernateUtils.geTCurrentSession();
            transaction = session.beginTransaction();

            Query query = session.createQuery("from User where name=:name and password=:password");
            query.setParameter("name",user.getName());
            query.setParameter("password",user.getPassword());
            List list = query.list();
            if(list.size()==0){//获取的列表行数为0，表示没有对应的用户
                throw new LoginException("账号密码错误");
            }else if(list.size()==1){//获取的列表行数为1，表示唯一对应的用户存在
                retrunUser=(User) list.get(0);
            }else  {//如果数据多条，那么数据库出现相同姓名，相同密码的用户，则爆出异常
                //在前端使用ajax 杜绝用户重名的现象
                throw new LoginException("服务器异常");
                //这里要么放回true，要么就抛出异常
            }
        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            //这里的异常并不是非常致命的异常，暂时忽悠它，后面会有解决方案
            throw new LoginException("账号密码错误");
        }finally {
            if(transaction!=null){
                try {
                    transaction.commit();
                }catch (Exception e){
                    throw new LoginException("账号密码错误");
                }
            }
            session.close();
            return  retrunUser;//这里可能放回unll。
        }
    }
    //忘记密码查询
    public  User forgetPassword(User user) throws LoginException {
        Session session = HibernateUtils.geTCurrentSession();
        Transaction transaction = session.beginTransaction();
        User retrunUser=null;
        try{
            Query query = session.createQuery("from User where name=:name and phoneNumber=:phoneNumber");
            query.setParameter("name",user.getName());
            query.setParameter("phoneNumber",user.getPhoneNumber());
            List list = query.list();
            if(list.size()==0){//获取的列表行数为0，表示没有对应的用户
                throw new LoginException("账号密码错误");
            }else if(list.size()==1){//获取的列表行数为1，表示唯一对应的用户存在
                retrunUser=(User) list.get(0);
            }else  {//如果数据多条，那么数据库出现相同姓名，相同密码的用户，则爆出异常
                //在前端使用ajax 杜绝用户重名的现象
                throw new LoginException("服务器异常");
                //这里要么放回true，要么就抛出异常
            }
        }catch (Exception e){
            transaction.rollback();
          throw new LoginException("服务器异常");
        }finally {
            if(transaction!=null){
                try {
                    transaction.commit();
                }catch (Exception e){
                    throw new LoginException("账号密码错误");
                }
            }
            session.close();
            return  retrunUser;//这里可能放回unll。
        }
    }
    //更新一个user的操作
    public void update(User user) throws LoginException {
        Session session = HibernateUtils.geTCurrentSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.update(user);//执行更新操作

        }catch (Exception e){
            throw  new RuntimeException("更新失败");
        }finally {
            if(transaction!=null){
                try {
                    transaction.commit();
                }catch (Exception e){
                    throw new LoginException("账号密码错误");
                }
            }
            session.close();
        }
    }

    //删除一个user的操作
    public void delete(User user) throws LoginException {
        Session session = HibernateUtils.geTCurrentSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.delete(user);//执行删除操作

        }catch (Exception e){
            transaction.rollback();
            throw  new LoginException("删除失败");
        }finally {
            if(transaction!=null){
                try {
                    transaction.commit();
                }catch (Exception e){
                    throw new LoginException("账号密码错误");
                }
            }
            session.close();
        }
    }

}
