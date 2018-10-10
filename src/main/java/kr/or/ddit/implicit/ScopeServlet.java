package kr.or.ddit.implicit;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SocpeServlet
 */
@WebServlet("/scopeServlet")
public class ScopeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageScope = request.getParameter("pageScope");
		String requestScope = request.getParameter("requestScope");
		String sessionScope = request.getParameter("sessionScope");
		String applicationScope = request.getParameter("applicationScope");
		
		//             jsp         /   servlet
		// page        page        /      x
		// requset     request     /   request
		// session     session     /   request.getSession()
		// application application /   getServletContext()
		
		// 속성 설정 : setAttribute(String name, Object value);
		// 속성 획득 : getAttribute(String name);
		
		// page 영역 : servlet에 해당 scope 없음
		
		// request 영역
		request.setAttribute("requestScope", requestScope + "_attribute");
		
		// session 영역
		HttpSession session = request.getSession();
		session.setAttribute("sessionScope", sessionScope + "_attribute");
		
		// application 영역
		ServletContext application = getServletContext();
		application.setAttribute("applicationScope", applicationScope + "_attribute");
		
		// dispatch 방식으로 값 넘기기(forward로 위임)
		RequestDispatcher rd = request.getRequestDispatcher("/implicit/scopeResult.jsp");
		rd.forward(request, response);
		
	}

}
