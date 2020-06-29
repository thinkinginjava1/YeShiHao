package choutuan.service;

import domain.User;
import org.junit.Test;
import service.UserService;

import javax.security.auth.login.LoginException;

public class TestUserService {
    private  UserService userService=new UserService();
    @Test
    public void fun1(){
        User userinfo=new User("yeshihao","1213");
        User user1 = null;
        try {
            user1 = userService.login(userinfo);
        } catch (LoginException e) {
            e.printStackTrace();
        }
        System.out.println(user1);
    }
    @Test
    public void register()  {
        User user=new User("ye","6732410");
        user.setAddress("天津");
        user.setGender("男");
        user.setBirthday("2000-10-1");

        user.setPhoneNumber("18133016308");
        try {
            userService.regist(user);
        } catch (LoginException e) {
            System.out.println(e);
        }
    }
    @Test
    public void forgetPassword(){
        User use=new User();
        use.setName("yeshihao5");
        use.setPhoneNumber("18133016308");
        try {
            User user = userService.forgetPassword(use);
            final String string = user.toString();
        } catch (LoginException e) {
            e.printStackTrace();
        }

    }
}
