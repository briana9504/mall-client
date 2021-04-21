package mall.client.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


@WebFilter("/*")//모든 실행 전에 실행됨 ex)"/public/IndexController"
public class EncodingFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//ServletRequest는 HttpServletRequest의 부모임
		
		request.setCharacterEncoding("utf-8");
		System.out.println("EncodingFilter 실행");
		chain.doFilter(request, response);//위로 적으면 request 요청 전, 아래로 적으면 response 요청 후
	}
}
