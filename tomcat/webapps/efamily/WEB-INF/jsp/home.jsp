<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to E-Family</title>
</head>
<body>
	<p><span>Hello <span id="userName"></span>, welcome to E-Family</span></p>
	
	<p><a href="auth/logout.do" >Logout</a></p>
	<p><a href="user.do" >User</a></p>
	
	<script src="js/jquery/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
	
	$(document).ready(function() {
		document.getElementById("userName").innerHTML = '<%=session.getAttribute("userName")%>';
	});
	
	</script>
</body>
</html>