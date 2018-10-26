package kr.or.ddit.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class RequestCountFilter implements Filter{
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		ServletContext application = request.getServletContext();
		Map<String, Integer> param = (Map<String, Integer>) application.getAttribute("p");
		
		// application 객체에 param 객체가 존재하는지 확인
		// 없으면 신규생성 후 저장
		if(param == null){
			param = new HashMap<String, Integer>();
		}
		
		String uri = ((HttpServletRequest)request).getRequestURI();
		Integer count = param.get(uri);
		
		// 최초 요청 시
		if(count == null){
			param.put(uri, 1);
		} else { // 최초 요청이 아닐 시
			param.put(uri, ++count);
		}
		application.setAttribute("p", param);
		
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
