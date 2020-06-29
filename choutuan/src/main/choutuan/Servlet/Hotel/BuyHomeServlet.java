package Servlet.Hotel;

import Utils.ToJsonString;
import domain.HotelHome;
import domain.User;
import service.HotelService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class BuyHomeServlet extends HttpServlet {
    private HotelService hotelService = new HotelService();

    protected void doGet(HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String home = request.getParameter("home");
        String substring = home.substring(1,4);
        //System.out.println(substring);
        HotelHome hotel = hotelService.getHotel(substring);
        String userJson = (String) request.getSession().getAttribute("User");
        User user = (User) ToJsonString.jsonToBean(new User(), userJson);

        hotel.setUser(user);
        hotelService.updateHotel(hotel);
        response.sendRedirect("hotel.jsp");
    }
}
