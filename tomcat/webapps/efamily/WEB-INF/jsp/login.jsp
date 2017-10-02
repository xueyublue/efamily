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
			<tr><td>User Name: </td><td><input type="text" name="username"/></td></tr>
			<tr><td>Password: </td><td><input type="password" name="password"/></td></tr>
			<tr><td colspan="2"><input type="button" onClick="login()" value="Login"/></td></tr>
		</table>
		<span id="loginResult"></span>
	</form>
	
	<script src="js/jquery.min.js"></script>
	<script type="text/javascript">
		window.onload = initPage;
		var xmlHttp;
		
		function initPage() {
			// init xmlHttp object
			if (window.XMLHttpRequest) {
				xmlHttp = new XMLHttpRequest();
			} else {
				xmlHttp = new AxtiveXObject("Microsoft.XMLHTTP");
			}
		}
		
		function login() {
			if (xmlHttp == null) {
				alert("Unable to create request!");
				return;
			}
			// Clear loginResult
			document.getElementById("loginResult").innerHTML = "";
			xmlHttp.open("POST", "auth/login.do?userId=dev&password=dev", true);
			xmlHttp.onreadystatechange = showLoginResult;
			xmlHttp.send(null);
		}
		
		function showLoginResult() {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					window.location.href = "home.do";
				} else {
					document.getElementById("loginResult").innerHTML = "Fail";
				}
			}
		}
	</script>
</body>
</html>