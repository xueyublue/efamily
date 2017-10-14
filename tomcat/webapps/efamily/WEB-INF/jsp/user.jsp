<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1">
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
		
	<!-- Notification Bar -->
	<div class="row hide">
		<div class="col-xs-12">
			<ol class="breadcrumb">
				<li>Administrator</li>
				</ol>
			</div>
		</div>
	
	<!-- Buttons -->
	<div class="row">
		<div class="col-xs-5">
			<button class="btn btn-sm btn-primary" data-toggle="modal" data-target="#popup_addUser">
				<span class="glyphicon glyphicon-plus"></span>Add</button>
			<!-- POPUP > Add User -->
			<div class="modal fade" id="popup_addUser">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header"><strong>Add User</strong></div>
						<div class="modal-body">
							<form role="form" class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label" for="userId_add">User Id</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="userId_add" placeholder="User Id"></input>
									</div>
									</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="userName">User Name</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="userName_add" placeholder="User Name"></input>
									</div>
									</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="password1_add">Password</label>
									<div class="col-sm-9">
										<input type="password" class="form-control" id="password1_add" placeholder="Password"></input>
									</div>
									</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="password2_add"></label>
									<div class="col-sm-9">
										<input type="password" class="form-control" id="password2_add" placeholder="Re-Enter Password"></input>
									</div>
									</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="roleId_add">Role</label>
									<div class="col-sm-9">
										<select id="roleId_add" class="form-control">
											<option>ADMIN</option>
											<option>STANDARD</option>
											<option>GUEST</option>
										</select>
									</div>
									</div>
							</form>
							</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-sm btn-primary" onclick="addUser()">Commit</button>
							<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">Cancel</button>
							</div>
					</div>
				</div>
				</div>
			</div>
		<div class="col-xs-7" align="right">
			<button class="btn btn-sm btn-primary"  id="btn_fastBackward"><span class="glyphicon glyphicon-fast-backward"></span></button>
			<button class="btn btn-sm btn-primary"  id="btn_backward"><span class="glyphicon glyphicon-backward"></span></button>
			<button class="btn btn-sm btn-primary"  id="btn_forward"><span class="glyphicon glyphicon-forward"></span></button>
			<button class="btn btn-sm btn-primary"  id="btn_fastForward"><span class="glyphicon glyphicon-fast-forward"></span></button>
			</div>
		</div>
		
	<!-- User List Table -->
	<div class="row">
		<div class="col-xs-12">
			<div class="table-responsive">
				<table class="table table-striped table-hover table-bordered">
					<thead>
						<tr>
							<th>Action</th>
							<th>User Id</th>
							<th>User Name</th>
							<th>Role Id</th>
							<th>Last Login Date</th></tr>
						</thead>
					<tbody>
						<c:forEach items="${users}" var="c">
							<tr>
								<td width="80" align="center">
									<button class="btn btn-xs btn-warning" data-toggle="modal" data-target="#popup_updateUser"><span class="glyphicon glyphicon-pencil"></span></button>
									<!-- POPUP > Update User -->
									<div class="modal fade" id="popup_updateUser">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header" align="left"><strong>Update User</strong></div>
													<div class="modal-body" align="left">
														</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-sm btn-primary" data-dismiss="modal">Commit</button>
														<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">Cancel</button>
														</div>
												</div>
											</div>
										</div>
									<button class="btn btn-xs btn-danger" data-toggle="modal" data-target="#popup_deleteUser"><span class="glyphicon glyphicon-remove"></span></button>
									<!-- POPUP > Delete User -->
									<div class="modal fade" id="popup_deleteUser">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header" align="left"><strong>Delete User</strong></div>
												<div class="modal-body" align="left">Are you sure to <span class="text-danger"><strong>DELETE</strong></span> ${c.userId}?</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="deleteUser('${c.userId}')">Confirm</button>
													<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">Cancel</button>
												</div>
											</div>
										</div>
										</div>
									</td>
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
	
	<!-- POPUPs -->
	
	<!-- End of Container -->
	</div>
	<script src="static/js/jquery/jquery.min.js"></script>
	<script src="static/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			document.getElementById("userName").innerHTML = '<%=session.getAttribute("userName")%>';
			$('#administrator').addClass('active');
			$('#btn_fastBackward').addClass('disabled');
			$('#btn_backward').addClass('disabled');
			$('#btn_forward').addClass('enabled');
			$('#btn_fastForward').addClass('enabled');
		});
		
		function queryUser() {
			// TODO:
		}
		
		function addUser() {
			var pwd1 = $('#password1_add').val();
			var pwd2 = $('#password2_add').val();
			if (pwd1 != pwd2) {
				alert("Passwrod is not match!");
			} else {
				$.ajax({
					type : "get",
					url : "user/addUser.do?" 
							+ "userId=" + $('#userId_add').val()
							+ "&userName=" + $('#userName_add').val()
							+ "&password=" + $('#password2_add').val()
							+ "&roleId=" + $('#roleId_add').val(),
					cache : false,
					async : true,
					success : function(obj) {
						window.location.href = "user.do";
					},
					error : function(obj) {
						var response = obj.responseText.replace('"', '').replace('"', '');
						alert(response);
					}
				});
			}
		}
		
		function deleteUser(userId) {
			$.ajax({
				type : "get",
				url : "user/deleteUser.do?" 
						+ "userId=" + userId,
				cache : false,
				async : true,
				success : function(obj) {
					window.location.href = "user.do";
				},
				error : function(obj) {
					// TODO: 
				}
			});
		}
	</script>
</body>
</html>