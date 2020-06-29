package service;

import dao.HotelRedisDao;
import domain.HotelHome;

import java.util.ArrayList;
import java.util.HashMap;

public class HotelService {
    //dao层会将所有的房间和房间名存在redis中
    //nameList只是一个维护
    //当namelist不存在对应的房间名后，不再显示那个房间，将取消去那个hotel的操作
    //其他的数据会保存再redis中，但是不能再进行操作，应为那些hotel属于酒店，
    private HotelRedisDao hotelRedisDao = new HotelRedisDao();
    ArrayList<String> nameList = null;
    ArrayList<HotelHome> hotelHomes = null;
    private static  HashMap<String, String> nameHomesKeyValue =null;
    public synchronized HashMap<String, String> getHotelNameList() {//返回没有住人的房间
        nameList = hotelRedisDao.getNameList();
        hotelHomes = hotelRedisDao.getHotelHomes(nameList);
        nameHomesKeyValue=new HashMap<>();
        for (int i = 0; i < hotelHomes.size(); i++) {
            if (String.valueOf(hotelHomes.get(i).getUser().getId()).equals("6")) {
                nameHomesKeyValue.put(nameList.get(i), String.valueOf(hotelHomes.get(i).getUser().getId()));
            }
        }
        return nameHomesKeyValue;//只放回list没人住的酒店名单
    }

    public void updateHotel(HotelHome hotelHome) {//传入hotel更新redis
        try {
            hotelRedisDao.toRedisMap(hotelHome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //得到一个房间
    public HotelHome getHotel(String hotelname) {
        HotelHome hotelHome = hotelRedisDao.getHotelHome(hotelname);
        return hotelHome;
    }
}
