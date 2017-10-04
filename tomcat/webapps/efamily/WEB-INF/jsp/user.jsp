<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
<style type="text/css">
	.userlist {
		border-right: 1px solid #000;
		border-bottom: 1px solid #000
	}
	.userlist th {
		border-left: 1px solid #000;
		border-top: 1px solid #000;
		text-align: center
	}
	.userlist td {
		border-left: 1px solid #000;
		border-top: 1px solid #000;
		text-align: center
	}
</style>
</head>
<body>
	<table class="userlist" border="0" cellspacing="0" cellpadding="4" align="center">
		<tr><td colspan="4" align="center">User List</td></tr>
		<tr><th>User Id</th>
			<th>User Name</th>
			<th>Role Id</th>
			<th>Last Login Date</th></tr>
		<c:forEach items="${users}" var="c">
			<tr><td><span id="userId">${c.userId}</span></td>
				<td><span id="userName">${c.userName}</span></td>
				<td><span id="roleId">${c.roleId}</span></td>
				<td><span id="lastLoginDate"><fmt:formatDate value="${c.lastLoginDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></td></tr>
		</c:forEach>
	</table>
	
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
			
			// Get all users
			// getAllUsers();
		}

		function getAllUsers() {
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