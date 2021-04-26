package mall.client.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.OrdersDao;
import mall.client.vo.Client;


@WebServlet("/OrdersListController")
public class OrdersListController extends HttpServlet {
	private OrdersDao ordersDao;
	//주문 목록보기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ordersDao = new OrdersDao();
		//로그인 유효성검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}	
		//세션에서 client 가져오기
		Client client = (Client)session.getAttribute("loginClient");
		//페이징 넣기

		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 15;
		int beginRow = (currentPage - 1)*rowPerPage;
		
		//최종 페이지
		int totalRow = ordersDao.totalCount(client);
		int lastPage = totalRow/rowPerPage;
		if(totalRow % rowPerPage != 0){
			lastPage +=1;
		}
		
		//dao연결 - list가져오기
		
		List<Map<String, Object>> ordersList = ordersDao.selectOrdersListByClient(beginRow, rowPerPage, client);
		//forward하기
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("ordersList", ordersList);
		request.getRequestDispatcher("/WEB-INF/view/orders/ordersListTest.jsp").forward(request, response);
	}

}
