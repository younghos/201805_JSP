package kr.or.ddit.filter;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FilterServlet
 */
@WebServlet("/filterServlet")
public class FilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("filter servlet doGet");
		request.getRequestDispatcher("/filter/view.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// charterset 설정을 filter로 이관(코드 중복제거)
//		request.setCharacterEncoding("UTF-8");
		
		// userNm 파라미터 받기
		String userNm = request.getParameter("userNm");
		
		// request 속성으로 저장
		request.setAttribute("userNm", userNm);
		
		// request forward(/filter/view.jsp)
		request.getRequestDispatcher("/filter/view.jsp").forward(request, response);
		
		
	}

}
