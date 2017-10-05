<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
<link href="static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
	.userlist {
		border-right: 1px solid #000;
		border-bottom: 1px solid #000;
	}
	.userlist th {
		border-left: 1px solid #000;
		border-top: 1px solid #000;
		text-align: center;
	}
	.userlist td {
		border-left: 1px solid #000;
		border-top: 1px solid #000;
		text-align: center;
	}
</style>
<script src="static/js/jquery/jquery.min.js"></script>
<script src="static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	// TODO: 
</script>
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
</body>
</html>