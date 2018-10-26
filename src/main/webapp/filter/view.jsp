<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>view.jsp</title>
</head>
<body>
view.jsp
<%--post --%>
<form action="/filterServlet" method="post">
	<input type="text" name="userNm" value="브라운"/>
	<button type="submit">전송</button>
</form>

<h2>filterServlet 에서 받은 값</h2>
<h3>${userNm }</h3>
</body>
</html>