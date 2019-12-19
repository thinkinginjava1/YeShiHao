package choutuan.service;

import dao.HotelRedisDao;
import domain.HotelHome;
import domain.User;
import org.junit.Test;
import service.HotelService;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class TestHotelService {
    private HotelRedisDao hotelRedisDao=new HotelRedisDao();
    private HotelService hotelService=new HotelService();
    @Test
    public void gethotel(){
        HashMap<String, String> hotelNameList = hotelService.getHotelNameList();
        System.out.println(hotelNameList+"test");
    }
    @Test
    public void updateHotel() {//再redis更新一个hotel
        HashMap<String, String> hotelNameList = hotelService.getHotelNameList();
        HotelHome hotelHome = hotelRedisDao.getHotelHome("211");
        User user=new User();
        user.setId(1L);
        hotelHome.setUser(user);
        try {
            hotelRedisDao.toRedisMap(hotelHome);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
//    @Test
//    public void get(){
//        int mediumHomeNumber = hotelRedisDao.getMediumHomeNumber();
//        System.out.println(mediumHomeNumber);
//        int maxHomeNumber = hotelRedisDao.getMaxHomeNumber();
//        System.out.println(maxHomeNumber);
//        int i = hotelRedisDao.getsmallHomeNumber();
//        System.out.println(i);
//    }

    @Test
    public void setRedisHome(){
        hotelRedisDao.setRedisHome();
    }
    @Test
    public void redisToMysql(){
        hotelRedisDao.redisToMysql();
    }
}
