package Servlet.User;

import domain.User;
import service.UserService;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class Register extends HttpServlet {
    private UserService userService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Map<String, String[]> parameterMap = request.getParameterMap();
        String name=parameterMap.get("name")[0];
        String password=parameterMap.get("password")[0];
        String gender=parameterMap.get("gender")[0];
        String birthday=parameterMap.get("birthday")[0];
        String address=parameterMap.get("address")[0];
        String phoneNumber=parameterMap.get("phoneNumber")[0];
        User user=new User(name,password,gender,birthday,address,phoneNumber);
        try {
            userService.regist(user);
            response.sendRedirect("User/Login.jsp");//注册完成，跳到登录界面
        } catch (LoginException e) {
            request.getSession().setAttribute("registinfo","注册失败");
            response.sendRedirect("User/register.jsp");
        }
    }
}
