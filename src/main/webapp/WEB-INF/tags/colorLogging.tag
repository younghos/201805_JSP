<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="length" required="false" type="java.lang.Integer" %>
<%@ attribute name="color" required="true" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--
	length 속성이 없을 경우 length 기본값 20으로
 --%>
length : ${(length == null) ? 20 : length }<br>
<c:set var ="length" value="${(length == null) ? 20 : length }" />
<font color="${color }">
<%--
	length : 5 이면
	
	=====logging=====

--%>
<c:forEach begin="1" end="${length }">=</c:forEach>
logging
<c:forEach begin="1" end="${length }">=</c:forEach>
</font>