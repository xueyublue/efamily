<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to E-Family</title>
</head>
<body>
	<span>Hello <span id="userName"></span>, welcome to E-Family</span>
	
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
			// get user info from session and set it to components
			setUserInfo();
		}
		
		function setUserInfo() {
			document.getElementById("userName").innerHTML = '<%=session.getAttribute("userName")%>';
		}
	</script>
</body>
</html>