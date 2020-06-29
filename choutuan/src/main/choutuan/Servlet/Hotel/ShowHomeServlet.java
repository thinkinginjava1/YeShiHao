package Servlet.Hotel;

import Utils.RedisUtil;
import Utils.ToJsonString;
import domain.Hotel;
import domain.HotelHome;
import redis.clients.jedis.Jedis;
import service.HotelService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class ShowHomeServlet extends HttpServlet {
    private HotelService hotelService = new HotelService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //这里先将redis数据库中的数据拿出来，加载好后，每次访问
        //HotelHome,分成3层操作

        //确实，每次刷新页面，必须连接服务器的，当时将数据放在了redis中，每次请求的就是redis中的数据
        HashMap<String, String> hotelNameList = hotelService.getHotelNameList();
        //System.out.println(hotelNameList.toString()+"service");
        Set<String> roomName = hotelNameList.keySet();
        Hotel hotel=new Hotel();
        for(String name:roomName){
            HotelHome hotelhome = hotelService.getHotel(name);
            hotelhome.setUser(null);
            hotel.add(hotelhome);
        }
        String string = ToJsonString.toJsonString(hotel, "hotels");
        request.getSession().setAttribute("hotelString",string);
        request.getRequestDispatcher("hotel.jsp").forward(request,response);
    }
}
