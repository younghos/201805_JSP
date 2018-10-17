package kr.or.ddit.prod.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceInf;
import kr.or.ddit.util.model.PageVo;

/**
 * Servlet implementation class ProdServlet
 */
@WebServlet(urlPatterns={"/prodPageList", "/prodDetail"})
public class ProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println("prodServlet doGet : "+uri);
		
		if(uri.equals("/prodPageList")){
			prodPageList(request, response);
		} else if(uri.equals("/prodDetail")){
			prodDetail(request, response);
		}
	}
	
	private void prodDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("prodDetail");
		
		ProdServiceInf service = new ProdService();
		
		String prod_id = request.getParameter("prodId");
		ProdVo prodVo = service.selectProdDetail(prod_id);
		
		request.setAttribute("prodVo", prodVo);
		
		RequestDispatcher rd = request.getRequestDispatcher("/prod/prodDetail.jsp");
		rd.forward(request, response);
	}

	/**
	* Method : prodPageList
	* 작성자 : pc24
	* 변경이력 :
	* @param request
	* @param response
	* @throws ServletException
	* @throws IOException
	* Method 설명 : 제품 페이지 리스트 조회
	*/
	private void prodPageList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("prodPageList");
		
		ProdServiceInf service = new ProdService();
		
		PageVo pageVo = new PageVo();
		pageVo.setPage(Integer.parseInt(request.getParameter("page")));
		pageVo.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
		
		Map<String, Object> resultMap = service.selectProdPageList(pageVo);
		
		// 페이지 리스트
		List<ProdVo> prodList = (List<ProdVo>) resultMap.get("prodList");
		
		// 페이지 건수
		int pageCnt = (int) resultMap.get("pageCnt");
		
		// request 객체에 저장
		request.setAttribute("prodList", prodList);
		request.setAttribute("pageCnt", pageCnt);
		
		// forward(userAllList.jsp --> userPagingList.jsp)
		RequestDispatcher rd = request.getRequestDispatcher("/prod/prodPagingList.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
