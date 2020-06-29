package Listener;

import Utils.HibernateUtils;
import Utils.RedisUtil;
import dao.HotelRedisDao;
import org.hibernate.Session;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    //在这里初始化工具类

    //在这里初始化hibernate和redis类
    private  Session session = HibernateUtils.geTCurrentSession();
    private  Jedis jedis = RedisUtil.getJedis();
   private   final HotelRedisDao hotelRedisDao=new HotelRedisDao();
    // Public constructor is required by servlet spec
    public MyListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
        hotelRedisDao.setRedisHome();//数据载入redis，再初始化的时候只载入一次
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                hotelRedisDao.redisToMysql();
                System.out.println("正在写入数据到mysql中"+new Date());
            }//1000*60*10
        },1000*10,1000*60*60);//十秒后 六十分钟调用一次
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
        hotelRedisDao.redisToMysql();

    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
