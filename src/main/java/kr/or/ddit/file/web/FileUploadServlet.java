package kr.or.ddit.file.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5) //파일 전송을 위해 필요 (maxfileSize 최대크기, maxRequestSize 전송할수 있는 최대개수)
@WebServlet("/fileUpload")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// fileUpload.jsp 전달
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/fileupload/fileUpload.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 일반 text 파라미터 : request.getParameter("파라미터명");
		// 파일 : request.getPart("파라미터명");
		
		System.out.println(request.getContentType());
		System.out.println("userId : " + request.getParameter("userId"));
//		System.out.println("profile : " + request.getParameter("profile"));
		
		// profile part
		Part profilePart = request.getPart("profile");
		System.out.println("profile part : "+ profilePart.getContentType());
		System.out.println("Content-disposition : " + profilePart.getHeader("Content-disposition"));
		System.out.println("profile size : "+profilePart.getSize());
		
		String contentDisposition = profilePart.getHeader("Content-disposition");
		
		String fileName = "";
		
		String[] splits = contentDisposition.split("; ");
		
		for (String str : splits) {
			if(str.indexOf("filename=") >=0){
				fileName = str.substring(10, str.lastIndexOf("\""));
			}
		}
		
		// 파일 쓰기
		profilePart.write("D:\\A_TeachingMaterial\\6.JspSrpgin\\upload\\"+fileName);
//		new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		profilePart.delete(); // 임시영역을 지우는 역할 (파일이 커서 못올라가면 임시영역에 저장된다)
	}

}
