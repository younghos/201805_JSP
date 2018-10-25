<%@tag import="java.sql.SQLException"%>
<%@tag import="java.sql.DriverManager"%>
<%@tag import="java.sql.ResultSet"%>
<%@tag import="java.sql.PreparedStatement"%>
<%@tag import="java.sql.Connection"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="code" required="true" type="java.lang.String" %>
code : ${code }<br>

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
			
			String code = (String)jspContext.getAttribute("code");
			
			String sql = "select prod_id, prod_name from prod where prod_lgu = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);
			
			rs = pstmt.executeQuery();
			
			out.write("<select>");
			while(rs.next()){
					
				out.write("	<option value=\""+rs.getString("prod_id")+"\">"+rs.getString("prod_name")+"</option>");
			
			}
			out.write("</select>");
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