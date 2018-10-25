<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>selectLang.jsp</title>
<script src="/js/jquery-3.3.1.min.js"></script>

</head>
<body>
<%-- 
	1. lang 이라고 이름지어진 파라미터로 locale을 설정
	2. 만약 lang 파라미터가 없을 경우 ko를 기본값으로 사용
	3. select box가 변경될때 해당 언어로 페이지 재요청
	4. select box는 현재 요청된 lang 파라미터 언어값이 선택되어져 있어야함
	   (lang 파라미터가 없을경우 기본값 ko)
	   
	최초 접속 : http://localhost:8081/jstl/fmt/selectLang.jsp
	그 이후 : select box를 변경하여 페이지 재요청
 --%>
<c:set var="lang" value="${(param.lang == null) ? 'ko' : param.lang }"/>

lang : ${lang } <br>

<form id="frm" action="selectLang.jsp">
	<select id="lang" name="lang">
		<option value="ko">한국어</option>
		<option value="ja">日本語</option>
		<option value="en">English</option>
	</select>
</form>
<fmt:setLocale value="${lang }"/>
<fmt:bundle basename="kr.or.ddit.resource.msg.msg">
	<fmt:message key="hello"></fmt:message>
	<fmt:message key="visitor">
		<fmt:param value="brown"></fmt:param>
	</fmt:message>
</fmt:bundle>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$("#lang").val("${lang}");
	
	$("#lang").change(function(){
		$("#frm").submit();
	});
});
</script>
</html>