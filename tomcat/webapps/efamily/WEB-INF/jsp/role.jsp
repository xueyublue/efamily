<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Role</title>
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
				<li><a href="" class="active">Role</a></li>
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
							<th>Role Id</th>
							<th>Role Name</th>
							<th>Admin Flag</th>
							<th>Guest Flag</th>
							<th>Expiry Date</th></tr>
						</thead>
					<tbody>
						<c:forEach items="${roles}" var="c">
							<tr>
								<td width="80" align="center">
									<button class="btn btn-xs btn-warning" onclick="popup_updateRole('${c.roleId}')"><span class="glyphicon glyphicon-pencil"></span></button>
									<button class="btn btn-xs btn-danger" onclick="popup_deleteRole('${c.roleId}')"><span class="glyphicon glyphicon-remove"></span></button>
									</td>
								<td><span id="roleId">${c.roleId}</span></td>
								<td><span id="roleName">${c.roleName}</span></td>
								<td><span id="adminFlag">${c.adminFlag}</span></td>
								<td><span id="guestFlag">${c.guestFlag}</span></td>
								<td><span id="expiryDate"><fmt:formatDate value="${c.expiryDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></td></tr>
							</c:forEach>
						</tbody>
				</table>
				</div>	
			</div>
		</div>
	
	<!-- POPUPs -->
	<!-- POPUP > Add Role -->
	<div class="modal fade" id="popup_addUser">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header"><strong>Add Role</strong></div>
				<div class="modal-body">
					<form role="form" class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-3 control-label" for="txt_roleId_add">Role Id</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="txt_roleId_add" placeholder="Role Id"></input>
							</div>
							</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="txt_roleName_add">Role Name</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="txt_roleName_add" placeholder="Role Name"></input>
							</div>
							</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="sel_adminFlag_add">Admin Flag</label>
							<div class="col-sm-9">
								<select id="sel_adminFlag_add" class="form-control">
									<option>0</option>
									<option>1</option>
								</select>
							</div>
							</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="sel_guestFlag_add">Guest Flag</label>
							<div class="col-sm-9">
								<select id="sel_guestFlag_add" class="form-control">
									<option>0</option>
									<option>1</option>
								</select>
							</div>
							</div>
					</form>
					</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-primary" onclick="addRole()">Commit</button>
					<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">Cancel</button>
					</div>
			</div>
		</div>
		</div>
				
	<!-- POPUP > Update Role -->
	<div class="modal fade" id="popup_updateRole">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header"><strong>Update Role</strong></div>
				<div class="modal-body">
					<form role="form" class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-3 control-label" for="txt_roleId_update">Role Id</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="txt_roleId_update" placeholder="Role Id"></input>
							</div>
							</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="txt_roleName_update">Role Name</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="txt_roleName_update" placeholder="New Role Name"></input>
							</div>
							</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="sel_adminFlag_update">Admin Flag</label>
							<div class="col-sm-9">
								<select id="sel_adminFlag_update" class="form-control">
									<option>0</option>
									<option>1</option>
								</select>
							</div>
							</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="sel_guestFlag_update">Guest Flag</label>
							<div class="col-sm-9">
								<select id="sel_guestFlag_update" class="form-control">
									<option>0</option>
									<option>1</option>
								</select>
							</div>
							</div>
					</form>
					</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-primary" onclick="updateRole()">Commit</button>
					<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">Cancel</button>
					</div>
			</div>
		</div>
		</div>
		
	<!-- POPUP > Delete Role -->
	<div class="modal fade" id="popup_deleteRole">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" align="left"><strong>Delete Role</strong></div>
				<div class="modal-body" align="left">Are you sure to <span class="text-danger"><strong>DELETE</strong></span> <span id="lbl_roleId_delete"></span>?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="deleteRole()">Confirm</button>
					<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">Cancel</button>
				</div>
			</div>
		</div>
		</div>
		
	<!-- End of Container -->
	</div>
	
	<script src="static/js/jquery/jquery.min.js"></script>
	<script src="static/bootstrap/js/bootstrap.min.js"></script>
	
	<!-- Customize Scripts -->
	<script type="text/javascript">
		$(document).ready(function() {
			document.getElementById("userName").innerHTML = '<%=session.getAttribute("userName")%>';
			$('#administrator').addClass('active');
			$('#btn_fastBackward').addClass('disabled');
			$('#btn_backward').addClass('disabled');
			$('#btn_forward').addClass('enabled');
			$('#btn_fastForward').addClass('enabled');
		});
		
		function popup_updateRole(roleId) {
			if(roleId) {
				$.ajax({
					type : "get",
					url : "role/getRole.do?",
					data: {'roleId' : roleId},
					dataType: 'json',
					cache : false,
					async : true,
					success : function(obj) {
						if (obj) {
							// var user = $.parseJSON(obj);
							var role = obj;
							$('#txt_roleId_update').val(role.roleId);
							$('#txt_roleName_update').val(role.roleName);
							$('#sel_adminFlag_update').val(role.adminFlag);
							$('#sel_guestFlag_update').val(role.guestFlag);
							$("#txt_roleId_update").attr("disabled", "disabled");
							$('#popup_updateRole').modal('show');
						}
					},
					error : function(obj) {
						if (obj.status == '901') {
							window.location.href = "login.do";
						} else {
							var response = obj.responseText.replace('"', '').replace('"', '');
							alert(response);	
						}
					}
				});	
			}
		}
		
		function popup_deleteRole(roleId) {
			$('#lbl_roleId_delete').text(roleId);
			$('#popup_deleteRole').modal('show');
		}
		
		function addRole() {
			var adminFlag = $('#sel_adminFlag_add').val();
			var guestFlag = $('#sel_adminFlag_add').val();
			if (adminFlag == '1' && guestFlag == '1') {
				alert("Admin-Flag and Guest-Flag cannot both be 1!");
			} else {
				$.ajax({
					type : "get",
					url : "role/addRole.do?",
					data: {'roleId' : $('#txt_roleId_add').val(),
						'roleName' : $('#txt_roleName_add').val(), 
						'adminFlag' : $('#sel_adminFlag_add').val(), 
						'guestFlag' : $('#sel_guestFlag_add').val()},
					dataType: 'json',
					cache : false,
					async : true,
					success : function(obj) {
						window.location.href = "role.do";
					},
					error : function(obj) {
						if (obj.status == '901') {
							window.location.href = "login.do";
						} else {
							var response = obj.responseText.replace('"', '').replace('"', '');
							alert(response);	
						}
					}
				});
			}
		}
		
		function updateRole() {
			var adminFlag = $('#sel_adminFlag_update').val();
			var guestFlag = $('#sel_adminFlag_update').val();
			if (adminFlag == '1' && guestFlag == '1') {
				alert("Admin-Flag and Guest-Flag cannot both be 1!");
			} else {
				$.ajax({
					type : "get",
					url : "role/updateRole.do?",
					data: {'roleId' : $('#txt_roleId_update').val(),
						'roleName' : $('#txt_roleName_update').val(), 
						'adminFlag' : $('#sel_adminFlag_update').val(), 
						'guestFlag' : $('#sel_guestFlag_update').val()},
					dataType: 'json',
					cache : false,
					async : true,
					success : function(obj) {
						window.location.href = "role.do";
					},
					error : function(obj) {
						if (obj.status == '901') {
							window.location.href = "login.do";
						} else {
							var response = obj.responseText.replace('"', '').replace('"', '');
							alert(response);	
						}
					}
				});
			}
		}
		
		function deleteUser() {
			var userId = $('#lbl_roleId_delete').text();
			if(userId) {
				$.ajax({
					type : "get",
					url : "role/deleteRole.do?",
					data: {'roleId' : roleId},
					dataType: 'json',
					cache : false,
					async : true,
					success : function(obj) {
						window.location.href = "role.do";
					},
					error : function(obj) {
						if (obj.status == '901') {
							window.location.href = "login.do";
						} else {
							var response = obj.responseText.replace('"', '').replace('"', '');
							alert(response);	
						}
					}
				});	
			}
		}
	</script>
</body>
</html>