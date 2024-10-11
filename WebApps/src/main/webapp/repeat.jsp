<%@ taglib prefix="c" uri="/WEB-INF/jsp2/jsp2-example-taglib.tld" %>
<html>
  <head>
    <title>JSP Tag</title>
  </head>
  <body>
  	<h1>
  	</h1>
    <hr>
    <b><u>Result:</u></b><br>
    <c:repeat num="10">
      <b>Invocation</b> ${count} of ${num}<br>
    </c:repeat>
  </body>
</html>