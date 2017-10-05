<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login to E-Family</title>
<script src="static/js/jquery/jquery-3.2.1.min.js"></script>
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
	<form>
		<table>
			<tr><td colspan="2">Login to E-Family</td></tr>
			<tr><td>User Name: </td><td><input type="text" name="userId" id="userId"/></td></tr>
			<tr><td>Password: </td><td><input type="password" name="password" id = "password"/></td></tr>
			<tr><td colspan="2"><input type="button" onClick="login()" value="Login"/></td></tr>
		</table>
		<span id="loginResult"></span>
	</form>
</body>
</html>