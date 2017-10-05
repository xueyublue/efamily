<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
<link href="static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
	
</style>
</head>
<body>
<div class="container">
	<table class="table table-striped table-hover">
		<thead>
			<tr><th>User Id</th>
				<th>User Name</th>
				<th>Role Id</th>
				<th>Last Login Date</th></tr>
			</thead>
		<tbody>
			<c:forEach items="${users}" var="c">
				<tr><td><span id="userId">${c.userId}</span></td>
					<td><span id="userName">${c.userName}</span></td>
					<td><span id="roleId">${c.roleId}</span></td>
					<td><span id="lastLoginDate"><fmt:formatDate value="${c.lastLoginDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></td></tr>
			</c:forEach>
			</tbody>
	</table>
	</div>
	<script src="static/js/jquery/jquery.min.js"></script>
	<script src="static/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		// TODO: 
	</script>
</body>
</html>