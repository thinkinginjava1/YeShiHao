package Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest servletRequest= (HttpServletRequest) req;
        HttpServletResponse servletResponse= (HttpServletResponse) resp;
        Object user = servletRequest.getSession().getAttribute("User");
        if(user==null){
            servletResponse.sendRedirect("User/Login.jsp");
        }else {
            chain.doFilter(req, resp);//这就是放行
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
