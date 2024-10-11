<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="javax.naming.InitialContext,ru.specialist.viewmodel.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Курсы</h1>
	
	<jsp:useBean id="search" class="ru.specialist.viewmodel.SearchVM" />
	<jsp:setProperty name="search" property="*" />
	<form>
		<input type=text name=search value="${search.search}">
		<input type=submit value="поиск">
	</form>
	

	<%
    InitialContext co = new InitialContext();
    pageContext.setAttribute("ds_jndi", co.lookup("java:comp/env/jdbc/WebDS"));
	%>
	
	<s:query var="courses" dataSource="${ds_jndi}">
	    SELECT title, length, description FROM Courses 
	    WHERE title LIKE ?
	    ORDER BY title
	    <s:param value="${search.searchString}"/>
	</s:query>
	
	
	<table border=1>
	<c:forEach items="${courses.rows}" var="row">
		<tr>
			<td>
				<c:out value="${row.Title}" />
			</td>
			<td>
				<c:out value="${row.Length}" />
			</td>
			<td>
				<c:out value="${row.Description}" />
			</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>