package choutuan.dao;

import Utils.TestRunTime;
import dao.HotelRedisDao;
import dao.UserDao;
import domain.HotelHome;
import domain.User;
import org.junit.Test;

public class AddHotelHome {
    private UserDao userDao=new UserDao();
    private HotelRedisDao hotelRedisDao=new HotelRedisDao();
    @Test
    public  void addHotelHome(){
        TestRunTime.start();
        HotelHome hotelHome=null;
        for(Long i=306l;i<400L;i++){
            hotelHome=new HotelHome();
            hotelHome.setUser(null);
            if(i%3==0) {
                hotelHome.setPrices("5000");
                hotelHome.setVolume(6L);
            }else if(i%7==0){
                hotelHome.setPrices("1000");
                hotelHome.setVolume(2L);
            }else {
                hotelHome.setPrices("500");
                hotelHome.setVolume(1L);
            }
            hotelHome.setName(i);
            hotelRedisDao.addHotelHome(hotelHome);
        }
        System.out.println(TestRunTime.getTimeUse());
    }
}
