<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
<div class="container">
	<p><span>Hello <span id="userName"></span>, welcome to E-Family</span><a href="auth/logout.do" >Logout</a></p>
	<div class="btn-group">
		<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Administrator</button>
		<ul class="dropdown-menu">
			<li><a href="user.do">User</a></li>
			<li><a href="#">Role</a></li>
			<li><a href="#">Application Log</a></li>
			<li class="divider"></li>
			<li><a href="#">Others</a></li></ul>
		</div>
	</div>
</body>
</html>