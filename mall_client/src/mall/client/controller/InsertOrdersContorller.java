package mall.client.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.CartDao;
import mall.client.model.OrdersDao;
import mall.client.vo.Cart;
import mall.client.vo.Client;
import mall.client.vo.Orders;


@WebServlet("/InsertOrdersContorller")
public class InsertOrdersContorller extends HttpServlet {
	private OrdersDao ordersDao;
	private CartDao cartDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		//의존객체 생성 후 주입
		this.ordersDao = new OrdersDao();
		this.cartDao = new CartDao();
		
		//request
		int ebookNo = Integer.parseInt(request.getParameter("ebookNo"));
		Client client = (Client)session.getAttribute("loginClient");
		
		Orders orders = new Orders();
		orders.setClientNo(client.getClientNo());
		orders.setEbookNo(ebookNo);
		
		Cart cart = new Cart();
		cart.setClientMail(client.getClientMail());
		cart.setEbookNo(ebookNo);
		System.out.println(cart.toString()+"insertOrdersController의 카트확인");
		//dao 주문 후 장바구니지움
		//insert 후 delete되기 전 db에 문제가 생기면?--> insert도 취소(롤백)--> 트랜잭션처리
		ordersDao.insertOrders(orders);
		cartDao.deleteCart(cart);
		
		response.sendRedirect(request.getContextPath()+"/OrdersListController");
	}

}
