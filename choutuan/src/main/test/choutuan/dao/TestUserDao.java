package choutuan.dao;

import Utils.HibernateUtils;
import dao.UserDao;
import domain.User;
import org.hibernate.Session;
import org.junit.Test;

import javax.security.auth.login.LoginException;

public class TestUserDao {
    private UserDao dao=new UserDao();
    @Test
    public void regist() throws LoginException {
        User user=new User("叶仕昊","6732410");
        user.setAddress("天津");
        user.setGender("男");
        user.setBirthday("2000-10-1");

        user.setPhoneNumber("18133016308");
       dao.regist(user);
    }
    @Test
    public void login() throws LoginException {
        User userinfo=new User("叶仕昊","6732410a");
        User user = dao.login(userinfo);

    }
    @Test
    public void update(){
        User user=new User("litinahan","123456");
        user.setAddress("tianjingdainzi");
        user.setGender("男");
        user.setBirthday("10-2");

        user.setId(6L);
        try {
            dao.update(user);
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void forgetPassword(){
        User use=new User();
        use.setName("yeshihao5");
        use.setPhoneNumber("18133016308");
        try {
            User user = dao.forgetPassword(use);
            String string = user.toString();

        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getSessionFactory(){
        Session session = HibernateUtils.geTCurrentSession();

    }
}
