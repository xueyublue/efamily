<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to E-Family</title>
</head>
<body>
	<p><span>Hello <span id="userName"></span>, welcome to E-Family</span></p>
	
	<p><a href="user.do" onClick="gotoUser()">User</a></p>
	
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
			// Set UserInfo
			setUserInfo();
		}
		
		function setUserInfo() {
			document.getElementById("userName").innerHTML = '<%=session.getAttribute("userName")%>';
		}
		
		function gotoUser() {
			window.location.href = "user.do";
		}
	</script>
</body>
</html>