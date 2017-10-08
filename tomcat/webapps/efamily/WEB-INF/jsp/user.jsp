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
	<!-- Navigation Bar -->
	<div class="row">
		<div class="col-xs-12">
			<jsp:include page="navbar.jsp"></jsp:include>
			</div>
		</div>
	
	<!-- Page Content -->
	
	<!-- Location -->
	<div class="row">
		<div class="col-xs-12">
			<ol class="breadcrumb">
				<li>Administrator</li>
				<li><a href="" class="active">User</a></li>
				</ol>
			</div>
		</div>
	
	<!-- Buttons -->
	<div class="row">
		<div class="col-xs-5">
			<button class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-plus"></span></button>
			<button class="btn btn-sm btn-warning"><span class="glyphicon glyphicon-pencil"></span></button>
			<button class="btn btn-sm btn-danger"><span class="glyphicon glyphicon-remove"></span></button>
			</div>
		<div class="col-xs-7" align="right">
			<button class="btn btn-sm btn-primary">Select All</button>
			<button class="btn btn-sm btn-primary">UnSelect All</button>
			</div>
		</div>
		
	<!-- User List Table -->
	<div class="row">
		<div class="col-xs-12">
			<div class="table-responsive">
				<table class="table table-striped table-hover table-bordered">
					<thead>
						<tr>
							<th>Select</th>
							<th>User Id</th>
							<th>User Name</th>
							<th>Role Id</th>
							<th>Last Login Date</th></tr>
						</thead>
					<tbody>
						<c:forEach items="${users}" var="c">
							<tr>
								<td width="50" align="center"><input type="checkbox"/></td>
								<td><span id="userId">${c.userId}</span></td>
								<td><span id="userName">${c.userName}</span></td>
								<td><span id="roleId">${c.roleId}</span></td>
								<td><span id="lastLoginDate"><fmt:formatDate value="${c.lastLoginDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></td></tr>
						</c:forEach>
						</tbody>
				</table>
				</div>	
			</div>
		</div>
		
	<!-- End of Container -->
	</div>
	<script src="static/js/jquery/jquery.min.js"></script>
	<script src="static/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			document.getElementById("userName").innerHTML = '<%=session.getAttribute("userName")%>';
			$('#administrator').addClass('active');
		});
	</script>
</body>
</html>