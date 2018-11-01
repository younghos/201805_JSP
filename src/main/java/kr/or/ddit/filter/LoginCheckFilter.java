package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.encrypt.sha.KISA_SHA256;
import kr.or.ddit.user.model.UserVo;

public class LoginCheckFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		String uri = ((HttpServletRequest)request).getRequestURI();
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		UserVo userVo = (UserVo) session.getAttribute("S_USER");
		System.out.println(userVo);
		
		if(uri.equals("/") || uri.equals("/login/login.jsp") || uri.equals("/dditLogin") || uri.startsWith("/css") || uri.startsWith("/js")){
			chain.doFilter(request, response);
		} else{
			if(userVo == null){
				request.getRequestDispatcher("/").forward(request, response);
			} else{
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
