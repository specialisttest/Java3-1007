<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="javax.naming.InitialContext"%>
<!-- Основные теги создания циклов, определения условий, вывода информации на страницу и т.д.  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--  Теги для работы с XML-документами -->
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<!--  Теги для работы с базами данных -->
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql"%>

<!--  Теги для форматирования и интернационализации информации (i10n и i18n) -->
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- отображение текста 16+64*2  -->
	<H2>
		<c:out value="16+64*2" />
	</H2>

	<!-- отображение значения 144 -->
	<H2>
		<c:out value="${16+64*2}" />
	</H2>


	<c:set var="user" scope="session" value="<i>Sergey</i>" />
	<H2>
		<c:out value="${user}" escapeXml="true" />
	</H2>

	<table cellpadding="3" cellspacing="0" border="1">
		<tr>
			<th>Контекст</th>
			<th>Описание</th>
		</tr>
		<tr>
			<td>pageScope</td>
			<td>Контекст страницы. Переменные доступны только для текущей
				страницы.</td>
		</tr>
		<tr>
			<td>requestScope</td>
			<td>Контекст запроса. Переменные доступны на всех страницах, а
				также сервлетам, обслуживающим текущий запрос пользователя.</td>
		</tr>
		<tr>
			<td>sessionScope</td>
			<td>Контекст сессии. Переменные доступны в течение всей сессии
				пользователя, т.е. пока не будет закрыт браузер или не закончится
				предельное время бездействия.</td>
		</tr>
		<tr>
			<td>applicationScope</td>
			<td>Контекст приложения. Доступ к переменным возможен на всех
				страницах веб-приложения. Это самый глобальный контекст.</td>
		</tr>
		<tr>
			<td>param</td>
			<td>В этом контексте располагаются параметры, отправленные
				пользователем/браузером либо как параметры адресной строки, либо как
				поля html-формы.</td>
		</tr>
		<tr>
			<td>paramValues</td>
			<td>Список значений параметров, которые были переданы серверу
				приложений. Формат отличается от предыдущего контекста. Если там <i>param</i>
				имел тип HashMap&lt;String, String&gt;, то здесь HashMap&lt;String,
				String [] &gt;.
			</td>
		</tr>
		<tr>
			<td>header</td>
			<td>В этом объекте хранится информация об http-заголовках,
				которые были переданы от браузера/клиента веб-серверу.</td>
		</tr>
		<tr>
			<td>headerValues</td>
			<td>Список значений http-заголовков.</td>
		</tr>
		<tr>
			<td>initParam</td>
			<td>Конфигурационные параметры, указанные для страницы или
				сервлета в дескрипторе приложений web.xml</td>
		</tr>
		<tr>
			<td>cookie</td>
			<td>Список переменных, помещенных внутрь cookie.</td>
		</tr>
		<tr>
			<td>pageContext</td>
			<td>Ссылка на объект pageContext, автоматически создаваемых
				внутри jsp-страницы.</td>
		</tr>
	</table>

	<!--c:set target="${userName}" property="fio" value="Остап Бендер" /-->
	<!--c:out value="${userName.fio}" /-->

	<c:remove var="user" scope="request" />
	<c:set var="salary" scope="session" value="${23400*2}" />
	<c:if test="${salary > 45000}">
		<p>
			Salary =
			<c:out value="${salary}" />
		<p>
	</c:if>

	<h2>Bean</h2>
	<form>
		<label>Имя: </label>
		<input type=text name=name>
		<br>
		<label>Возраст: </label>
		<input type=number name=age>
		<br>
		<input type=submit value="to bean">
	</form>
	
	
	<!--  создаем объект логики, указываем его имя и JAVA-класс  -->
	<jsp:useBean id="mySelf" class="ru.specialist.model.Person" />
	
	
	
	<!-- теперь начинаем заполнять значениями поля этого класса-бина, синтаксис
 property=”*” означает, что все пришедшие параметры запроса должны быть помещены внутрь bean-а. -->
	<jsp:setProperty name="mySelf" property="*" />
	
	
	<!-- вариант, когда указывается конкретное имя свойства, которое нужно заполнить информацией, и имя поля из HTTP-запроса -->
	<!-- jsp:setProperty name="mySelf" property="name" param="userName" /-->
	<!--  можно присвоить атрибуту значение в виде константы -->
	<!-- jsp:setProperty name="mySelf" property="age" value="40" / -->
	<!-- использование, как будто бы в составе класса Person есть свойство result.
 для обращения к свойствам используются методы getter-ы,
 поэтому фактическое существование поля класса с именем result не существенно -->
	<h2>
		Hello
		<jsp:getProperty name="mySelf" property="result" />
	</h2>

	<%
    InitialContext co = new InitialContext();
    pageContext.setAttribute("ds_jndi", 
    		co.lookup("java:comp/env/jdbc/WebDS"));
	%>
	<c:set var="search" scope="session" value="%web%" />
	
	<s:query var="courses" dataSource="${ds_jndi}">
	    SELECT title, length, description FROM Courses 
	    WHERE title LIKE ?
	    ORDER BY title
	    <s:param value="${search}"/>
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