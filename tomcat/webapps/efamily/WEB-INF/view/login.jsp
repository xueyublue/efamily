<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login to E-Family</title>
<link href="static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
	<form class="col-xs-12 col-sm-6 col-sm-offset-3">
		<fieldset>
			<legend><strong>Login to E-Family</strong></legend>
			<div class="form-group">
				<label>User Name</label>
				<input type="text" class="form-control" placeholder="User Name" id="userId"/>
				</div>
			<div class="form-group">
				<label>Password</label>
				<input type="password" class="form-control" placeholder="Password" id="password"/>
				</div>
			<div class="checkbox">
				<label><input type="checkbox"/>Remember Password</label>
				</div>
			<button type="button" class="btn btn-primary col-xs-12" onclick="login()"><strong>Login</strong></button>
			</fieldset>
		</form>
	<div class="row">
		<div class="col-xs-12"><span id="loginResult"></span></div></div>
		</div>
	
	<script src="static/js/jquery/jquery.min.js"></script>
	<script src="static/bootstrap/js/bootstrap.min.js"></script>
	
	<!-- Customize Scripts -->
	<script type="text/javascript">
		function login() {
			$.ajax({
				type : "get",
				url : "auth/loginAuth.do?" 
						+ "userId=" + $('#userId').val()
						+ "&password=" + $('#password').val(),
				cache : false,
				async : true,
				success : function(obj) {
					window.location.href = "home.page";
				},
				error : function(obj) {
					$('#loginResult').text(obj.responseText.replace('"', '').replace('"', ''));
				}
			});
		}
	</script>
</body>
</html>