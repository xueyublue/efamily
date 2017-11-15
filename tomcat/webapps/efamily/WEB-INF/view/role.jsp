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
<link href="static/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="static/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<style type="text/css">
	
</style>
</head>
<body>
<div class="container">
	<!-- Navigation Bar -->
	<div class="row">
		<div class="col-xs-12">
			<jsp:include page="base/navbar.jsp"></jsp:include>
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
							<th>Administrator</th>
							<th>Guest</th>
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
								<!-- Can be updated by doing: $("#roleId_DEV").text('1'); -->
								<!--<td><span id="roleId_${c.roleId}">${c.roleId}</span></td>-->
								<td><span id="roleName">${c.roleName}</span></td>
								<td><span id="adminFlag">${c.adminFlag}</span></td>
								<td><span id="guestFlag">${c.guestFlag}</span></td>
								<td><span id="expiryDate"><fmt:formatDate value="${c.expiryDate}" pattern="yyyy-MM-dd"/></span></td></tr>
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
							<label class="col-sm-3 control-label" for="sel_adminFlag_add">Administrator</label>
							<div class="col-sm-9">
								<select id="sel_adminFlag_add" class="form-control">
									<option>No</option>
									<option>Yes</option>
								</select>
							</div>
							</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="sel_guestFlag_add">Guest</label>
							<div class="col-sm-9">
								<select id="sel_guestFlag_add" class="form-control">
									<option>No</option>
									<option>Yes</option>
								</select>
							</div>
							</div>
						<div class="form-group">
			                <label class="col-sm-3 control-label" for="dtp_expiryDate" >Expiry Date</label>
			                <div class="col-sm-9">
			                	<div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_expiryDate">
				                    <input class="form-control" size="16" type="text" value="" readonly id="txt_expiryDate_add">
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
				                	</div>
			                	<input type="hidden" id="dtp_expiryDate" value="" />
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
							<label class="col-sm-3 control-label" for="sel_adminFlag_update">Administrator</label>
							<div class="col-sm-9">
								<select id="sel_adminFlag_update" class="form-control">
									<option>No</option>
									<option>Yes</option>
								</select>
							</div>
							</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="sel_guestFlag_update">Guest</label>
							<div class="col-sm-9">
								<select id="sel_guestFlag_update" class="form-control">
									<option>No</option>
									<option>Yes</option>
								</select>
							</div>
							</div>
						<div class="form-group">
			                <label class="col-sm-3 control-label" for="dtp_expiryDate_update" >Expiry Date</label>
			                <div class="col-sm-9">
			                	<div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_expiryDate_update">
				                    <input class="form-control" size="16" type="text" value="" readonly id="txt_expiryDate_update">
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
				                	</div>
				                	<input type="hidden" id="dtp_expiryDate_update" value="" />
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
	
	<script type="text/javascript" src="static/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="static/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="static/datetimepicker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<script type="text/javascript" src="static/datetimepicker/js/locales/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
	
	<!-- Customize Scripts -->
	<script type="text/javascript">
		$(document).ready(function() {
			document.getElementById("userName").innerHTML = '<%=session.getAttribute("USER_NAME")%>';
			
			$('#administrator').addClass('active');
			$('#btn_fastBackward').addClass('disabled');
			$('#btn_backward').addClass('disabled');
			$('#btn_forward').addClass('enabled');
			$('#btn_fastForward').addClass('enabled');

			// Change DB value to display text
			// 1: Yes, 0: No
			$("tbody tr").each(function() {
				var td_adminFlag = $(this).children('td:eq(3)');
				if (td_adminFlag.text() == '1') {
					td_adminFlag.text('Yes');
				} else {
					td_adminFlag.text('No');
				}
				var td_guestFlag = $(this).children('td:eq(4)');
				if (td_guestFlag.text() == '1') {
					td_guestFlag.text('Yes');
				} else {
					td_guestFlag.text('No');
				}
			});

			// Initialize DateTimePicker
			$('.form_datetime').datetimepicker({
		        //language:  'fr',
		        weekStart: 1,
		        todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				minView: 2,
				forceParse: 0
		    });
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
							if (role.adminFlag == '1') {
								$('#sel_adminFlag_update').val('Yes');
							} else {
								$('#sel_adminFlag_update').val('No');
							}
							if (role.guestFlag == '1') {
								$('#sel_guestFlag_update').val('Yes');
							} else {
								$('#sel_guestFlag_update').val('No');
							}
							$('#txt_expiryDate_update').val(role.expiryDate);
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
			var roleId = $('#txt_roleId_add').val();
			var roleName = $('#txt_roleName_add').val();
			var adminFlag = $('#sel_adminFlag_add').val();
			var guestFlag = $('#sel_guestFlag_add').val();
			var expiryDate = $('#txt_expiryDate_add').val();
			
			if (adminFlag == 'Yes' && guestFlag == 'Yes') {
				alert("Admin-Flag and Guest-Flag cannot both be Yes!");
			} else if (!expiryDate) {
				alert("Expiry Date cannot be empty!");
			} else {
				if(adminFlag == 'Yes') {
					adminFlag = '1';
				} else {
					adminFlag = '0';
				}
				if(guestFlag == 'Yes') {
					guestFlag = '1';
				} else {
					guestFlag = '0';
				}
				$.ajax({
					type : "get",
					url : "role/addRole.do?",
					data: {'roleId' : roleId,
						'roleName' : roleName, 
						'adminFlag' : adminFlag, 
						'guestFlag' : guestFlag,
						'expiryDate' : expiryDate},
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
			var roleId = $('#txt_roleId_update').val();
			var roleName = $('#txt_roleName_update').val();
			var adminFlag = $('#sel_adminFlag_update').val();
			var guestFlag = $('#sel_guestFlag_update').val();
			var expiryDate = $('#txt_expiryDate_update').val();
			
			if (adminFlag == 'Yes' && guestFlag == 'Yes') {
				alert("Admin-Flag and Guest-Flag cannot both be Yes!");
			} else if (!expiryDate) {
				alert("Expiry Date cannot be empty!");
			} else {
				if(adminFlag == 'Yes') {
					adminFlag = '1';
				} else {
					adminFlag = '0';
				}
				if(guestFlag == 'Yes') {
					guestFlag = '1';
				} else {
					guestFlag = '0';
				}
				$.ajax({
					type : "get",
					url : "role/updateRole.do?",
					data: {'roleId' : roleId,
						'roleName' : roleName, 
						'adminFlag' : adminFlag, 
						'guestFlag' : guestFlag,
						'expiryDate' : expiryDate},
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
		
		function deleteRole() {
			var roleId = $('#lbl_roleId_delete').text();
			if(roleId) {
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