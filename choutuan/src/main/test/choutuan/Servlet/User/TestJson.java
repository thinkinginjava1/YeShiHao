package choutuan.Servlet.User;

import Utils.ToJsonString;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import domain.User;
import org.junit.Test;

public class TestJson {
    @Test
    public void fun1(){
        XStream xStream=new XStream(new JettisonMappedXmlDriver());
        User user=new User("yeshihao3","1212321");
        user.setAddress("tianjingdainzi");
        user.setGender("男");
        user.setBirthday("10-1");

        xStream.alias("user",User.class);
        xStream.setMode(XStream.NO_REFERENCES);
        String s = xStream.toXML(user);
        System.out.println(s);
    }
    @Test
    public void fun2(){
        User user=new User("yeshihao3","1212321");
        user.setAddress("tianjingdainzi");
        user.setGender("男");
        user.setBirthday("10-1");

        String string = ToJsonString.toUserJsonString(user, "user");
        System.out.println(string);
    }
}
