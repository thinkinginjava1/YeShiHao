package Servlet.User;

import domain.User;
import service.UserService;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForgetPasswordServlet extends HttpServlet {
    private UserService service=new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("changeId").trim();
        String phone = request.getParameter("changePhone").trim();
        User user=new User();
        user.setName(name);
        user.setPhoneNumber(phone);
        try {
            User user1 = service.forgetPassword(user);//放回这个user，因为下个页面要去修改这个user；
            request.getSession().setAttribute("ForgetUser",user1);
            response.sendRedirect("User/ChangePasswordTwo.jsp");
        } catch (LoginException e) {
            request.getSession().setAttribute("FroGetPassWordmsg","账号错误，或者密码错误");
            request.getRequestDispatcher("User/ChangePassword.jsp").forward(request,response);
        }
    }
}
