<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		String userName = null;
		
		if (request.getMethod().equals("POST"))
			userName = request.getParameter("userName");
		
		userName = (userName == null || userName.isBlank()) ? "" : userName.trim();
	
	%>
	<h1>
	<%
		if (userName.isEmpty())
			out.print("Привет!");
		else
			out.print(String.format("Привет, %s!", userName));
	%>
	</h1>
	<form method="POST">
		<label>Имя: </label>
		<input type="text" name="userName" value="<%= userName %>">
		<input type="submit" value="OK">
	</form>

</body>
</html>