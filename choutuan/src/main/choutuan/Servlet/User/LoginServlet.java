package Servlet.User;

import Filter.EncodingRequest;
import Utils.ToJsonString;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import domain.User;
import service.UserService;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.fail;

public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name").trim();
        String password = request.getParameter("password").trim();
        User userinfo = new User(name, password);
        //用用户名和密码作为查询条件，进行查询，返回一个完整数据库中User对象
        try {
            //这里要么放回true，要么抛出异常
            //从数据库中放回完整的对象
            User user = userService.login(userinfo);
            user.setPassword(null);//这里将password设置为null，这里的密码是不会直接作为json对象发过去的
            user.setHotels(null);
            String JsonUserString = ToJsonString.toUserJsonString(user, "user");
            //讲对象转为json对象，变成字符串并且保存在request域中

            request.setAttribute("UserJsonString", JsonUserString);
           request.getRequestDispatcher("index.jsp").forward(request, response);
            //重定向操作
        } catch (LoginException e) {
            //这里得到异常消息，并且处理异常消息，  要么时密码错误，或者服务器错误
            request.getSession().setAttribute("msg", "登录失败");
            response.sendRedirect("User/Login.jsp");
        }
    }
}

