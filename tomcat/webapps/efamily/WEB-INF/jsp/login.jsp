<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login to E-Family</title>
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
	
	<script src="js/jquery.min.js"></script>
	<script type="text/javascript">
		
		window.onload = initPage;
		var request = false;

		function initPage() {
			try {
				request = new XMLHttpRequest();
			} catch (trymicrosoft) {
				try {
					request = new ActiveXObject("Msxml2.XMLHTTP");
				} catch (e) {
					try {
						request = new ActiveXObject("Microsoft.XMLHTTP");
					} catch (failed) {
						request = false;
					}
				}
			}
			if (!request)
				alert("Error initializing XMLHttpRequest!");
		}

		function login() {
			if (request == null) {
				alert("Unable to create request!");
				return;
			}
			// Clear loginResult
			document.getElementById("loginResult").innerHTML = "";
			var userId = document.getElementById("userId").value;
			var password = document.getElementById("password").value;
			request.open("GET", "auth/loginAuth.do?userId=" + escape(userId) + "&password=" + escape(password), true);
			request.onreadystatechange = showLoginResult;
			request.send(null);
		}

		function showLoginResult() {
			if (request.readyState == 4) {
				if (request.status == 200) {
					window.location.href = "home.do";
				} else if (request.status == 404) {
					alert("Requested URL is not found.");
				} else if (request.status == 403) {
					alert("Access denied.");
				} else if (request.status == 401) {
					document.getElementById("loginResult").innerHTML = request.responseText.replace('"', '').replace('"', '');
				} else {
					document.getElementById("loginResult").innerHTML = request.status;
				}
			}
		}
	</script>
</body>
</html>