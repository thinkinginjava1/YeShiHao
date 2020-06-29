package Servlet.User;

import domain.User;
import service.UserService;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangePassWordServlet extends HttpServlet {
    private UserService userService=new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        User user= (User) request.getSession().getAttribute("ForgetUser");
        if(user!=null){
            user.setPassword(request.getParameter("password").trim());
            try {
                userService.update(user);//这边要么成功要么服务器那边抛出异常
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (LoginException e) {
                //这里表示更新失败
            }
        }
    }
}
