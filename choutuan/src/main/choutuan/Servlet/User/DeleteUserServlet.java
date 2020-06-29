package Servlet.User;

import Utils.ToJsonString;
import dao.UserDao;
import domain.User;
import service.UserService;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserServlet extends HttpServlet {
    private UserService userService=new UserService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = (String) request.getSession().getAttribute("User");
        User user1 = ToJsonString.userJsonToBean(new User(), user);
        request.getSession().removeAttribute("User");
        try {
            userService.delete(user1);
        } catch (LoginException e) {
            e.printStackTrace();
        }
        response.sendRedirect("index.jsp");
    }
}
