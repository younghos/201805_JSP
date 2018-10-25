<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>logging.jsp</title>
</head>
<body>
<tags:loggingTag />
1~10 출력 <br>
<c:forEach begin="1" end="10" step="1" var="i">
	${i } <br>
</c:forEach>
<tags:loggingTag />
<tags:colorLogging color="lime" /><br>
1~10 출력 <br>
<c:forEach begin="1" end="10" step="1" var="i">
	${i } <br>
</c:forEach>
<tags:colorLogging length="5" color="lime" />
</body>
</html>