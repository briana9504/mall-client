package mall.client.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import mall.client.model.StatsDao;


@WebListener
public class StatsListener implements HttpSessionListener {
	private StatsDao statsDao;
    //새로운 세션이 만들어졌을 때
    public void sessionCreated(HttpSessionEvent se)  { 
         //1) 오늘날짜의 카운트가 없으면 1을 입력
    	 //2) 오늘날짜의 카운트가 있으면 +1을 업데이트
    	if(se.getSession().isNew()) {
    	
	    	System.out.println("새로운 세션이 생성 되었습니다.");
	    	
	    	this.statsDao = new StatsDao();
	    	if(this.statsDao.selectStatsByToday() == null) {
	    		this.statsDao.insertStats();
	    	} else {
	    		this.statsDao.updateStats();
	    	}
	    	
    	}
    }
	
	//세션이 닫혔을 때
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }
	
}
