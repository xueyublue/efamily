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
	function login() {
		$.ajax({
			type : "get",
			url : "auth/loginAuth.do?" 
					+ "userId=" + $('#userId').val()
					+ "&password=" + $('#password').val(),
			cache : false,
			async : false,
			success : function(obj) {
				window.location.href = "home.do";
			},
			error : function(obj) {
				$('#loginResult').text(
						obj.responseText.replace('"', '').replace('"', ''));
			}
		});
	}
</script>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12">Login to E-Family</div>
	</div>
	<div class="row">
		<div class="col-md-12"><input type="text" name="userId" id="userId" placeholder="User Id"/></div>
	</div>
	<div class="row">
		<div class="col-md-12"><input type="password" name="password" id="password" placeholder="Password"/></div>
	</div>
	<div class="row">
		<div class="col-md-12"><input type="button" onClick="login()" value="Login"/></div>
	</div>
	<div class="row">
		<div class="col-md-12"><span id="loginResult"></span></div>
	</div>
</div>
</body>
</html>