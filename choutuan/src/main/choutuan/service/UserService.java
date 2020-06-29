package service;

import dao.UserDao;
import domain.User;

import javax.security.auth.login.LoginException;

public class UserService {
    private UserDao dao = new UserDao();

    public void regist(User usr) throws LoginException {
        dao.regist(usr);
    }

    public User login(User usr) throws LoginException {
        User temp = dao.login(usr);
        return temp;
    }

    public User forgetPassword(User usr) throws LoginException {
        User user = dao.forgetPassword(usr);
        if(user==null){
            throw  new RuntimeException("没有找到对应的账号和手机号");
        }
        return user;
    }

    public void update(User usr) throws LoginException {
        dao.update(usr);
    }

    public void delete(User user) throws LoginException {
        dao.delete(user);

    }
}
