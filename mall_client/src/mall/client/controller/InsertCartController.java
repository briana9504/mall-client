package mall.client.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.CartDao;
import mall.client.vo.*;

/**
 * Servlet implementation class InsertCartController
 */
@WebServlet("/InsertCartController")
public class InsertCartController extends HttpServlet {
	private CartDao cartDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 확인
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		//ebookOne에서 받은 ebookNo
		int ebookNo = Integer.parseInt(request.getParameter("ebookNo"));
		this.cartDao = new CartDao();
		
		//전처리
		Cart cart = new Cart();
		cart.setEbookNo(ebookNo);
		cart.setClientMail(((Client)session.getAttribute("loginClient")).getClientMail());
		System.out.println(cart.toString()+"<insertCartController>");
		
		//카트 중복검사 - 장바구니에 이미 존재하는지 확인 코드 추가
		if(this.cartDao.selectClientMail(cart) == true) {
			//dao 호출 - 장바구니에 넣기
			this.cartDao.insertCart(cart);
		} else {
			System.out.println("카트에 중복된 데이터 존재");		
		}
		
		response.sendRedirect(request.getContextPath()+"/CartListController");
	}
}
