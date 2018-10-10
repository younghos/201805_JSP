package kr.or.ddit.calculation;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/mulCalculation")
public class MulCalculation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/calculation/mulInput.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int start = Integer.parseInt(request.getParameter("param1"));
		int end = Integer.parseInt(request.getParameter("param2"));
		
		int mul = 1;
		if(start <= end){
			for(int i = start; i<=end ;i++){
				mul *= i;
			}
		} else{
			for(int i = end; i<= start; i++){
				mul *= i;
			}
		}
		
		System.out.print(start +" ~ "+end+" 사이 값의 합 : "+mul);
		
		HttpSession session = request.getSession();
		session.setAttribute("mulResult", mul);
		
		RequestDispatcher rd = request.getRequestDispatcher("/calculation/mulResult.jsp");
		rd.forward(request, response);
	}

}
