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
		//로그인 유효성검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}	
		//세션에서 clientMail 가져오기
		String clientMail = ((Client)session.getAttribute("loginClient")).getClientMail();
		System.out.printf("clientMail: %s<OrdersListController>\n", clientMail);
		//dao연결 - list가져오기
		ordersDao = new OrdersDao();
		List<Map<String, Object>> ordersList = ordersDao.selectOrdersList(clientMail);
		//forward하기
		request.setAttribute("ordersList", ordersList);
		request.getRequestDispatcher("/WEB-INF/view/client/ordersList.jsp").forward(request, response);
	}

}
