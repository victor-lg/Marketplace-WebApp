<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@600;900&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="css/errPage.css">
  <script src="https://kit.fontawesome.com/4b9ba14b0f.js"></script>
</head>
<body>
  <div class="mainbox">
  	<%
			int errorCode = (int)request.getAttribute("errorCode");

	%>  
	<p>El error obtenido ha sido: <%= errorCode %></p>
	<a href="login.html" class="logo" style="color: white; font-size: 40px">
		Back to home page
	</a>
  </div>
</body>
</html>