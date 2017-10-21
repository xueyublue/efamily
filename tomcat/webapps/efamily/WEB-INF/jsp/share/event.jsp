<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Event</title>
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
			<jsp:include page="../navbar.jsp"></jsp:include>
			</div>
		</div>
	
	<!-- Page Content -->
	
	<!-- Location -->
	<div class="row">
		<div class="col-xs-12">
			<ol class="breadcrumb">
				<li>Family Share</li>
				<li><a href="" class="active">Event</a></li>
				</ol>
			</div>
		</div>
		
	<!-- Notification Bar -->
	<div class="row hide">
		<div class="col-xs-12">
			<ol class="breadcrumb">
				<li>Family Share</li>
				</ol>
			</div>
		</div>
	
	<!-- Buttons -->
	<div class="row">
		<div class="col-xs-5">
			<button class="btn btn-sm btn-primary" data-toggle="modal" data-target="#popup_addEvent">
				<span class="glyphicon glyphicon-plus"></span>Add</button>
			</div>
		<div class="col-xs-7" align="right">
			<button class="btn btn-sm btn-primary"  id="btn_fastBackward"><span class="glyphicon glyphicon-fast-backward"></span></button>
			<button class="btn btn-sm btn-primary"  id="btn_backward"><span class="glyphicon glyphicon-backward"></span></button>
			<button class="btn btn-sm btn-primary"  id="btn_forward"><span class="glyphicon glyphicon-forward"></span></button>
			<button class="btn btn-sm btn-primary"  id="btn_fastForward"><span class="glyphicon glyphicon-fast-forward"></span></button>
			</div>
		</div>
		
	<!-- Event List Table -->
	<div class="row">
		<div class="col-xs-12">
			<div class="table-responsive">
				<table class="table table-striped table-hover table-bordered">
					<thead>
						<tr>
							<th>Action</th>
							<th>Title</th>
							<th>Location</th>
							<th>Start Date</th>
							<th>End Date</th>
							<th>Category</th></tr>
						</thead>
					<tbody>
						<c:forEach items="${events}" var="c">
							<tr>
								<td width="80" align="center">
									<button class="btn btn-xs btn-warning" onclick="popup_updateEvent('${c.eventId}')"><span class="glyphicon glyphicon-pencil"></span></button>
									<button class="btn btn-xs btn-danger" onclick="popup_deleteEvent('${c.eventId}')"><span class="glyphicon glyphicon-remove"></span></button>
									</td>
								<td><span id="title">${c.title}</span></td>
								<td width="200"><span>${c.location}</span></td>
								<td width="140"><span><fmt:formatDate value="${c.startDate}" pattern="yyyy-MM-dd HH:mm"/></span></td>
								<td width="140"><span><fmt:formatDate value="${c.endDate}" pattern="yyyy-MM-dd HH:mm"/></span></td>
								<td width="100"><span>${c.category}</span></td></tr>
							</c:forEach>
						</tbody>
				</table>
				</div>	
			</div>
		</div>
	
	<!-- POPUPs -->
	<!-- POPUP > Add Event -->
	<div class="modal fade" id="popup_addEvent">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header"><strong>Add Event</strong></div>
				<div class="modal-body">
					<form role="form" class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-3 control-label" for="txt_title_add">Title</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="txt_title_add" placeholder="Event Title"></input>
							</div>
							</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="txt_location_add">Location</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="txt_location_add" placeholder="Event Location"></input>
							</div>
							</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="sel_allDay_add">All Day</label>
							<div class="col-sm-9">
								<select id="sel_allDay_add" class="form-control">
									<option>No</option>
									<option>Yes</option>
								</select>
							</div>
							</div>
						<div class="form-group">
			                <label class="col-sm-3 control-label" for="dtp_startDate" >Start Date</label>
			                <div class="col-sm-9">
			                	<div class="input-group date form_datetime" data-date="2017-01-01T00:00:07Z" data-date-format="yyyy-mm-dd hh:ii:ss" data-link-field="dtp_startDate">
				                    <input class="form-control" size="16" type="text" value="" readonly id="txt_startDate_add">
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
				                	</div>
			                	<input type="hidden" id="dtp_startDate" value="" />
			                	</div>
			            	</div>
			            <div class="form-group">
			                <label class="col-sm-3 control-label" for="dtp_endDate" >End Date</label>
			                <div class="col-sm-9">
			                	<div class="input-group date form_datetime" data-date="2017-01-01T00:00:07Z" data-date-format="yyyy-mm-dd hh:ii:ss" data-link-field="dtp_endDate">
				                    <input class="form-control" size="16" type="text" value="" readonly id="txt_endDate_add">
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
				                	</div>
			                	<input type="hidden" id="dtp_endDate" value="" />
			                	</div>
			            	</div>
			            <div class="form-group">
							<label class="col-sm-3 control-label" for="sel_category_add">Category</label>
							<div class="col-sm-9">
								<select id="sel_category_add" class="form-control">
									<option>Appointment</option>
									<option>Social</option>
									<option>Travel</option>
									<option>Working</option>
									<option>OTHERS</option>
								</select>
							</div>
							</div>
					</form>
					</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-primary" onclick="addEvent()">Commit</button>
					<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">Cancel</button>
					</div>
			</div>
		</div>
		</div>
				
	<!-- POPUP > Delete Event -->
	<div class="modal fade" id="popup_deleteRole">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" align="left"><strong>Delete Event</strong></div>
				<div class="modal-body" align="left">Are you sure to <span class="text-danger"><strong>DELETE</strong></span> <span id="lbl_eventId_delete"></span>?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="deleteEvent()">Confirm</button>
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
			document.getElementById("userName").innerHTML = '<%=session.getAttribute("userName")%>';
			
			$('#family-share').addClass('active');
			$('#btn_fastBackward').addClass('disabled');
			$('#btn_backward').addClass('disabled');
			$('#btn_forward').addClass('enabled');
			$('#btn_fastForward').addClass('enabled');

			// Initialize DateTimePicker
			$('.form_datetime').datetimepicker({
		        //language:  'fr',
		        weekStart: 1,
		        todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				forceParse: 0,
		        showMeridian: 1
		    });
		});
		
		function popup_updateEvent(eventId) {
			if(eventId) {
				$.ajax({
					type : "get",
					url : "role/getRole.do?",
					data: {'eventId' : eventId},
					dataType: 'json',
					cache : false,
					async : true,
					success : function(obj) {
						if (obj) {
							// var user = $.parseJSON(obj);
							var role = obj;
							$('#txt_roleId_update').val(role.eventId);
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
		
		function popup_deleteEvent(eventId) {
			$('#lbl_roleId_delete').text(roleId);
			$('#popup_deleteRole').modal('show');
		}
		
		function addEvent() {
			var title = $('#txt_title_add').val();
			var location = $('#txt_location_add').val();
			var startDate = $('#txt_startDate_add').val();
			var endDate = $('#txt_endDate_add').val();
			var isAllDay = $('#sel_allDay_add').val();
			var category = $('#sel_category_add').val();
			
			if (!startDate) {
				alert("Start Date cannot be empty!");
			} else if (!endDate) {
				alert("End Date cannot be empty!");
			} else {
				if(isAllDay == 'Yes') {
					isAllDay = '1';
				} else {
					isAllDay = '0';
				}
				$.ajax({
					type : "get",
					url : "event/addEvent.do?",
					data: {'title' : title, 
						'location' : location, 
						'startDate' : startDate,
						'endDate' : endDate,
						'isAllDay' : isAllDay,
						'category' : category},
					dataType: 'json',
					cache : false,
					async : true,
					success : function(obj) {
						window.location.href = "event.do";
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
		
		function updateEvent() {
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
		
		function deleteEvent() {
			var eventId = $('#lbl_eventId_delete').text();
			if(eventId) {
				$.ajax({
					type : "get",
					url : "event/deleteEvent.do?",
					data: {'eventId' : eventId},
					dataType: 'json',
					cache : false,
					async : true,
					success : function(obj) {
						window.location.href = "event.do";
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