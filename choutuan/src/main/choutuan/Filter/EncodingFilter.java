package Filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EncodingFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        if(request.getMethod().equals("GET")){
            EncodingRequest encodingRequest=new EncodingRequest(request);
            filterChain.doFilter(encodingRequest,servletResponse);
        }else if(request.getMethod().equals("POST")){//post'sout
            servletRequest.setCharacterEncoding("utf-8");
            filterChain.doFilter(servletRequest,servletResponse);
        }
        }



//        servletRequest.setCharacterEncoding("utf-8");
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        /*
//         * 处理GET请求的编码问题
//         */
//// String username = request.getParameter("username");
//// username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
//        /*
//         * 调包request
//         * 1. 写一个request的装饰类
//         * 2. 在放行时，使用我们自己的request
//         */
//        if(req.getMethod().equals("GET")) {
//            EncodingRequest er = new EncodingRequest(req);
//            filterChain.doFilter(er, servletResponse);
//        } else if(req.getMethod().equals("POST")) {
//            System.out.println(req.getMethod());
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//

    @Override
    public void destroy() {

    }

}
