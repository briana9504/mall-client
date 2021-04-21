package mall.client.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class DBListener implements ServletContextListener {

	//꺼질때
    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("contextDestroyed");//메모리를 정리하는 중이므로 안나옴...
    }

    //켜질때
    public void contextInitialized(ServletContextEvent sce)  { 
    	try {
    		Class.forName("org.mariadb.jdbc.Driver");
    		System.out.println("mariadb로딩 성공");
    	} catch (ClassNotFoundException e){
    		e.printStackTrace();
    		System.out.println("mariadb로딩 실패");
    	}
    	
    }
	
}
