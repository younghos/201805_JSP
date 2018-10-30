package kr.or.ddit.file.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.user.service.UserServiceInf;

/**
 * Servlet implementation class FileDownloadServlet
 */
@WebServlet("/fileDownload")
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		UserServiceInf userService = new UserService();
		UserVo userVo = userService.selectUser(userId);
		
		File file = new File(userVo.getProfile());
		
		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[512];
		int length = 0;
		
		ServletOutputStream sos = response.getOutputStream();
		
		while((length = fis.read(buffer)) != -1){
			sos.write(buffer, 0, length);
		}
		
		sos.close();
		fis.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
