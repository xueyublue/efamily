<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login to E-Family</title>
<link href="static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="static/js/jquery/jquery.min.js"></script>
<script src="static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		document.getElementById("userName").innerHTML = '<%=session.getAttribute("userName")%>';
	});
</script>
</head>
<body>
	<p><span>Hello <span id="userName"></span>, welcome to E-Family</span></p>
	
	<p><a href="auth/logout.do" >Logout</a></p>
	<p><a href="user.do" >User</a></p>
</body>
</html>