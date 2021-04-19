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

import mall.client.model.ClientDao;
import mall.client.vo.Client;

/**
 * Servlet implementation class ClientOneController
 */
@WebServlet("/ClientOneController")
public class ClientOneController extends HttpServlet {
	private ClientDao clientdao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session clinet 정보받기
		HttpSession session = request.getSession();
		Client client = (Client)session.getAttribute("loginClient");
		
		//유효성 검사
		if(client == null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		
		System.out.println(client.toString()+"<ClientOneController.doget>");
		
		//dao에서 정보 호출
		clientdao = new ClientDao();
		List<Map<String, Object>> clientOneList = this.clientdao.selectClientOne(client);
		//clientOne으로 forward
		request.setAttribute("clientOneList", clientOneList);
		request.getRequestDispatcher("/WEB-INF/view/client/clientOne.jsp").forward(request, response);
	}


}
