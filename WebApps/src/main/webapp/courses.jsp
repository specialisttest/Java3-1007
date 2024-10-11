<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Курсы</h1>
	<% 
		//request
		//response
		//application
		//config
		//page
		//session
		//out
		
		request.setCharacterEncoding("utf-8");
		String search = null;
		if (request.getMethod().equals("POST"))
			search = request.getParameter("search");
		
		search = (search==null)?"":search.trim();
	
	%>
	<form method="POST">
		<label>Поиск курсов: </label>
		<input type="search" name="search" value="<%=search%>">
		<input type="submit" value="Найти">
	</form>	
	
	<%
	final String DRIVER_NAME = application.getInitParameter("DRIVER_NAME");
	final String CONNECTION_STRING = application.getInitParameter("CONNECTION_STRING");
	
	Class.forName(DRIVER_NAME); 

	try(Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
		String sql = "SELECT title, length,description FROM courses "
				+ "WHERE title LIKE ? "
				+ "ORDER BY title";
		PreparedStatement cmd = conn.prepareStatement(sql);
		cmd.setString( 1, "%" + search + "%");
		ResultSet result = cmd.executeQuery();
		
		out.println("<table border=1>");
		while( result.next() ) {
			out.println("<tr>");
			out.print("<td>");
			out.print(result.getString("title"));
			out.print("</td>");
			out.print("<td>");
			out.print(result.getInt("length"));
			out.print("</td>");
			out.print("<td>");
			out.print(result.getString("description"));
			out.print("</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		
		result.close();
	} //conn.close();	
	%>
	
</body>
</html>