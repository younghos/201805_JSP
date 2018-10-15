package kr.or.ddit.user.web;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.user.service.UserServiceInf;
import kr.or.ddit.util.StringUtil;

/**
 * Servlet implementation class UserFormServlet
 */
@MultipartConfig(maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
@WebServlet("/userForm")
public class UserFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// 사용자 등록 화면
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// userForm.jsp로 위임
		request.getRequestDispatcher("/user/userForm.jsp").forward(request, response);
	}

	// 사용자 등록
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// post 한글 파라미터 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		
		Part profilePart = request.getPart("profile");
		String contentDisposition = profilePart.getHeader("Content-disposition");
		
		String fileName = StringUtil.getFileNAmeFromHeader(contentDisposition);
		
		// url 정보를 실제 파일경로로 변경
		String path = getServletContext().getRealPath("/profile");
		
		profilePart.write(path+File.separator+fileName); // File.separator OS에 알맞은 중간자를 생성해서 넣어줌
		profilePart.delete();

		String profile = "/profile/"+fileName;
		
		// 파라미터 받아오기
		// userId, name, pass, addr1, addr2, zipcd, birth, email, tel
		UserVo userVo = new UserVo();
		userVo.setProfile(profile);
		userVo.setUserId(request.getParameter("userId"));
		userVo.setName(request.getParameter("name"));
		userVo.setPass(request.getParameter("pass"));
		userVo.setAddr1(request.getParameter("addr1"));
		userVo.setAddr2(request.getParameter("addr2"));
		userVo.setZipCd(request.getParameter("zipcd"));
		String str = request.getParameter("birth");
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = format.parse(str);
			userVo.setBirth(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		userVo.setEmail(request.getParameter("email"));
		userVo.setTel(request.getParameter("tel"));
		
		
		System.out.println(userVo.toString());
		
		// 사용자 등록 서비스 호출
		UserServiceInf service = new UserService();
		int insertCnt = service.insertUser(userVo);
		
		// 사용자 리스트로 이동(redirect : 서버 상태가 변경되므로 사용자가 새로고침을 통해 재요청시 중복 등록되는 현상을 막는다
		if(insertCnt!=0){
			response.sendRedirect("/userPageList?page=1&pageSize=10");
		} else{
			response.sendRedirect("/userForm");
		}
	}

}
