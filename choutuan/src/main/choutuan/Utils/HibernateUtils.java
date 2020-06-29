package Utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.net.URL;


public class HibernateUtils {
    HibernateUtils hibernateUtils=new HibernateUtils();
    private static final SessionFactory sessionFactory;
 //   private static ThreadLocal<Session> threadLocal=new ThreadLocal<>();
    static {
        try {
            URL url = HibernateUtils.class.getClassLoader().getResource("hibernate.cfg.xml");
            Configuration configuration = new Configuration();
            configuration.configure(url);
            sessionFactory = configuration.buildSessionFactory();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public  static Session openSession(){
//        Session session = threadLocal.get();
//        if(session==null){
//            threadLocal.set(sessionFactory.openSession());
//        }
//        session=threadLocal.get();
        return sessionFactory.openSession();
    }
    public  static  Session geTCurrentSession(){
        return  sessionFactory.getCurrentSession();
        //只有在配置中把session和thread绑定后才能使用这个方法
    }
}
