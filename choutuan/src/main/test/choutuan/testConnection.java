package choutuan;

import Utils.HibernateUtils;
import domain.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.io.Serializable;
import java.util.Date;

public class testConnection {
    @Test
    public void fun1(){
        Session session = HibernateUtils.geTCurrentSession();
            User user1=new User("yeshihao","1213");
        Transaction transaction = session.beginTransaction();
        session.save(user1);
        transaction.commit();
        session.close();

    }
}
