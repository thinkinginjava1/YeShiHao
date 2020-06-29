package Filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingRequest extends HttpServletRequestWrapper {
    private HttpServletRequest request;
    public EncodingRequest(HttpServletRequest request) {
        super(request);
        this.request=request;
    }

    @Override
    public String getParameter(String name) {
//        String value=request.getParameter(name);
//        try {
//            value=new String(value.getBytes("iso-8859-1"),"utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        //应为我从前直接将服务器的代码方式改掉了，所以这里方法不适用
        //the_variable_coding_method
        return request.getParameter(name);
    }



}
