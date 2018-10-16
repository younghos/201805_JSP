<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>lotto.jsp</title>
<script src="/js/jquery-3.3.1.min.js"></script>

</head>
<body>
	<%
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "pc24";
			String pass = "java";
			
			conn = DriverManager.getConnection(url, user, pass);
			
			int random = (int)(Math.random()*19+1);
			
			String sql = "select name from jspstudent where no = "+random;
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
	%>
				<p hidden id="hidden"><%=rs.getString("name") %></p>
	<%			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} finally{
			// 6. 자원 반납
			if(rs!=null) try {	rs.close(); } catch (Exception e) { e.printStackTrace(); }
			if(pstmt!=null) try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
			if(conn!=null) try { conn.close(); } catch (Exception e) { e.printStackTrace(); }
		}	
	%>
	<canvas id="myCanvas" width="200" height="200" style="border:1px solid #000;"></canvas>
</body>
<script type="text/javascript">
	var canvas = document.getElementById("myCanvas");
	var ctx = canvas.getContext("2d");
// // 	ctx.fillStyle = "#F00";
// // 	ctx.fillRect(0,0,150,75);
	
	ctx.beginPath();
	// 원의 중심 x좌표, 원의 중심 y좌표, 반지름
	ctx.arc(100, 100, 100, 0, 2*Math.PI);
	ctx.stroke();
	
	ctx.font = "60px Arial";
	var str = document.getElementById("hidden").innerText;
	ctx.fillText(str, 10, 120);		
</script>
</html>