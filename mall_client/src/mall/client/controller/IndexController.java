package mall.client.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mall.client.model.*;
import java.util.*;
import mall.client.vo.*;
// C -> M -> V
@WebServlet("/IndexController")
public class IndexController extends HttpServlet {
	private EbookDao ebookDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request 처리
		int currnetPage = 1;
		if(request.getParameter("currentPage") != null) {
			currnetPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 15;
		int beginRow = (currnetPage - 1)*rowPerPage;
		
		//model 호출
		this.ebookDao = new EbookDao();
		List<Ebook> ebookList = this.ebookDao.selectEbookListByPage(beginRow, rowPerPage);
		
		//마지막 페이지
		int totalRow = ebookDao.totalCount();
		int lastPage = totalRow/rowPerPage;
		if(totalRow % rowPerPage != 0){
			lastPage +=1;
		}
		
		//(View forward)index.jsp파일 연결
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("ebookList", ebookList);
		request.setAttribute("currentPage", currnetPage);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
		rd.forward(request, response);
	}

	

}
