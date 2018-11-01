package kr.or.ddit.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.encrypt.sha.KISA_SHA256;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.user.service.UserServiceInf;

public class LoginServlet extends HttpServlet {
	
	// 2.
	private final String USERID = "brown";
	private final String USERPW = "pass1234";
	
	UserServiceInf service = new UserService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String newParameter = req.getParameter("newParameter");
		System.out.println("newParameter : "+newParameter);
		
		// 1. 사용자 아이디와 비밀번호를 request객체에서 받아온다
		// 2. db에서 조회해온 아이디, 비밀번호를 체크를 한다.
		// 3-1. 일치할 경우 main.jsp로 이동
		// 3-2. 불일치할 경우 login.jsp로 이동
		
		// 1.
		String userId = req.getParameter("user_id");
		String userPw = req.getParameter("user_pw");
		// remember-me 파라미터 받아서 sysout 출력
		String rememberChk = req.getParameter("remember-me");
		
		// remember-me == null : 아이디 기억 사용안함
		if(rememberChk == null){
			Cookie[] cookies = req.getCookies();
			for(Cookie cookie : cookies){
				System.out.println(cookie.getName());
				if(cookie.getName().equals("remember") || cookie.getName().equals("userId")){
					cookie.setMaxAge(0);
					resp.addCookie(cookie);
				}
			}
		} else{
			// response 객체에 쿠기 저장
			Cookie cookie = new Cookie("remember", "Y");
			Cookie userId_cookie = new Cookie("userId", userId);
//			cookie.setMaxAge(0); // 0 값을 주면 쿠키의 유효시간이 없어짐
			
			resp.addCookie(cookie);
			resp.addCookie(userId_cookie);
		}
		
		// 2.--> db대신 상수로 대체 -> db로 대체
		// 1. 사용자가 전송한 userId 파라미터로 사용자 정보조회
		UserVo userVo = service.selectUser(userId);
		// 2. db에서 조회한 사용자 비밀번호가 파라미터로 전송된 비밀번호와 동일한지 비교
		boolean check = false;
//		check = userVo.getPass().equals(userPw);
		// 3. session에 사용자 정보등록(as-is : 임의의 userVo 등록, to-be : db에서 조회한 userVo)
		
		String encryptPass = KISA_SHA256.encrypt(userPw); // 비밀번호 암호화 적용
		check = userVo.authPass(encryptPass);
		
		if(userVo != null && check != false){
			HttpSession session = req.getSession();
			session.setAttribute("S_USER", userVo);
			
			RequestDispatcher rd = req.getRequestDispatcher("main.jsp");
			rd.forward(req, resp);
		} else{
			resp.sendRedirect("login/login.jsp");
		}
		/*
		// 3-1. : main.jsp 이동
		if(USERID.equals(userId) && USERPW.equals(userPw)){
			// redirect 방식(URL주소가 변경)
//			resp.sendRedirect("main.jsp?user_id="+userid+"&user_pw="+userpw);
			
			// 1). session에 사용자 정보 설정
			UserVo userVo = new UserVo();
			userVo.setUserId(userId);
			userVo.setName("브라운");
			userVo.setAlias("곰");
			userVo.setBirth(new Date());
			
			HttpSession session = req.getSession();
			session.setAttribute("userVo", userVo);
			
			// 2). main.jsp
			// body 영역에
			// 이름[별명] 님 안녕하세요.
			
			// dispatch 방식
			RequestDispatcher rd = req.getRequestDispatcher("main.jsp");
			rd.forward(req, resp);
		} else{ // 3-2. login.jsp 이동
			resp.sendRedirect("login/login.jsp");
		}
		*/
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8"); 
		PrintWriter pw = resp.getWriter();
		
		String[] useridValues = req.getParameterValues("user_id");
		String userpw = req.getParameter("user_pw");
		
		pw.println("<!DOCTYPE html>");
		pw.println("<html>");
		pw.println("	<head>");
		pw.println("		<meta charset=\"UTF-8\">");
		pw.println("		<title>loginServlet.java</title>");
		pw.println("	</head>");
		pw.println("	<body>");
		for(int i=0;i<useridValues.length;i++){
			pw.print("		userid : "+useridValues[i] + "<br>");
		}
		pw.println("		userpw : "+userpw);
		pw.println("	</body>");
		pw.println("</html>");
//		doPost(req, resp);
	}
}
