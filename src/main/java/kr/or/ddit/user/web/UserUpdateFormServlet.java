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
 * Servlet implementation class UserUpdateFormServlet
 */
@MultipartConfig(maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet("/userUpdateForm")
public class UserUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");

		UserServiceInf service = new UserService();
		UserVo userVo = service.selectUser(userId);

		request.setAttribute("userVo", userVo);
		request.getRequestDispatcher("/user/userUpdateForm.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// post 한글 파라미터 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		
		UserServiceInf service = new UserService();
		String userId = request.getParameter("userId");
		
		String profile = null;
		
		if(request.getParameter("profile") == null){
			UserVo user = service.selectUser(userId);
			profile = user.getProfile();
		} else{
			Part profilePart = request.getPart("profile");
			
			String contentDisposition = profilePart.getHeader("Content-disposition");
			
			String fileName = StringUtil.getFileNAmeFromHeader(contentDisposition);
			
			// url 정보를 실제 파일경로로 변경
			String path = getServletContext().getRealPath("/profile");

			profilePart.write(path + File.separator + fileName); // File.separator
																	// OS에 알맞은 중간자를
																	// 생성해서 넣어줌
			profilePart.delete();

			profile = "/profile/" + fileName;
		}
		
		UserVo userVo = new UserVo();
		userVo.setProfile(profile);
		userVo.setUserId(userId);
		userVo.setPass(request.getParameter("pass"));
		userVo.setName(request.getParameter("name"));
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

		int updateCnt = service.updateUser(userVo);

		System.out.println(updateCnt);

		if (updateCnt != 0) {
			response.sendRedirect("/userPageList?page=1&pageSize=10");
		} else {
			response.sendRedirect("/userUpdateForm");
		}

	}

}
