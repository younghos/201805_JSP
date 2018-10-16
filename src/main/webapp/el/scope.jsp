<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scope.jsp</title>
</head>
<body>
	<!-- el scope 확인 예제 -->
	<%--
		page(pageContext) : jsp 페이지에서만 유효한 속성
		request           : 요청이 끝날 때까지 유효한 속성
		session           : session이 만료되기 전까지 유효한 속성 (세션이 만료되는 시점 ? : server 종료, 
							  											         tomcat 설정에 따라, 
																		         session.invalidate(), 
																		           사용자가 server timeout 시간안에 요청을 하지 않을 때)
		application       : was(서버)에 하나 존재 (서버 종료 전까지는 유효한 속성)
	 --%>
	 <%
	 	pageContext.setAttribute("attribute", "pageContextAttribute");
	 	request.setAttribute("attribute", "requestAttribute");
	 	session.setAttribute("attribute", "sessionAttribute");
	 	application.setAttribute("attribute", "applicationAttribute");
	 %>
	 
	 pageContext : <%=pageContext.getAttribute("attribute") %> <br>
	 request     : <%=request.getAttribute("attribute") %> <br>
	 session     : <%=session.getAttribute("attribute") %> <br>
	 application : <%=application.getAttribute("attribute") %> <br>
	 
	 <h3>EL</h3>
	 <!-- 범위가 좁은 데부터 큰순서대로 검색해서 출력 -->
	 el pageContext : ${pageScope.attribute} <br>
	 el request     : ${requestScope.attribute} <br>
	 el session     : ${sessionScope.attribute} <br>
	 el application : ${applicationScope.attribute} <br>
</body>
</html>