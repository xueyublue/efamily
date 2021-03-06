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
			<jsp:include page="../base/navbar.jsp"></jsp:include>
			</div>
		</div>
	
	<!-- Page Content -->
	
	<!-- Location -->
	<div class="row">
		<div class="col-xs-12">
			<ol class="breadcrumb" style="font-style: italic;">
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
								<td width="100"><span>${c.category}</span></td>
								</tr>
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
									<option value="0">No</option>
									<option value="1">Yes</option>
								</select>
							</div>
							</div>
						<div class="form-group">
			                <label class="col-sm-3 control-label">Start Date</label>
			                <div class="col-sm-9">
			                	<div class="input-group date form_datetime">
				                    <input class="form-control" size="16" type="text" value="" readonly id="txt_startDate_add">
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
				                	</div>
			                	</div>
			            	</div>
			            <div class="form-group">
			                <label class="col-sm-3 control-label">End Date</label>
			                <div class="col-sm-9">
			                	<div class="input-group date form_datetime">
				                    <input class="form-control" size="16" type="text" value="" readonly id="txt_endDate_add">
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
				                	</div>
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

	<!-- POPUP > Update Event -->
	<div class="modal fade" id="popup_updateEvent">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header"><strong>Update Event</strong></div>
				<div class="modal-body">
					<form role="form" class="form-horizontal">
						<div class="form-group">
							<span class="hide" id="txt_eventId_update"></span>
							<label class="col-sm-3 control-label" for="txt_title_update">Title</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="txt_title_update" placeholder="Event Title"></input>
							</div>
							</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="txt_location_update">Location</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="txt_location_update" placeholder="Event Location"></input>
							</div>
							</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="sel_allDay_update">All Day</label>
							<div class="col-sm-9">
								<select id="sel_allDay_update" class="form-control">
									<option value="0">No</option>
									<option value="1">Yes</option>
								</select>
							</div>
							</div>
						<div class="form-group">
			                <label class="col-sm-3 control-label">Start Date</label>
			                <div class="col-sm-9">
			                	<div class="input-group date form_datetime">
				                    <input class="form-control" size="16" type="text" value="" readonly id="txt_startDate_update">
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
				                	</div>
			                	</div>
			            	</div>
			            <div class="form-group">
			                <label class="col-sm-3 control-label">End Date</label>
			                <div class="col-sm-9">
			                	<div class="input-group date form_datetime">
				                    <input class="form-control" size="16" type="text" value="" readonly id="txt_endDate_update">
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
				                	</div>
			                	</div>
			            	</div>
			            <div class="form-group">
							<label class="col-sm-3 control-label" for="sel_category_update">Category</label>
							<div class="col-sm-9">
								<select id="sel_category_update" class="form-control">
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
					<button type="button" class="btn btn-sm btn-primary" onclick="updateEvent()">Commit</button>
					<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">Cancel</button>
					</div>
			</div>
		</div>
		</div>

	<!-- POPUP > Delete Event -->
	<div class="modal fade" id="popup_deleteEvent">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" align="left"><strong>Delete Event</strong></div>
				<div class="modal-body" align="left">Are you sure to <span class="text-danger"><strong>DELETE</strong></span> <span id="lbl_eventId_delete" class="hide"></span><span id="lbl_eventDetails"></span>?</div>
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
			document.getElementById("userName").innerHTML = '<%=session.getAttribute("USER_NAME")%>';
			
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
		        showMeridian: 1,
		        format : 'yyyy-mm-dd hh:ii'
		    });

		    $('#sel_allDay_add').bind('change', function(){
		    	if($(this).val() == '0'){
		    		// Must detach old format dirst
      				$('.form_datetime').datetimepicker('remove');
      				$('.form_datetime').datetimepicker({
				        //language:  'fr',
				        weekStart: 1,
				        todayBtn:  1,
						autoclose: 1,
						todayHighlight: 1,
						startView: 2,
						forceParse: 0,
				        showMeridian: 1,
				        format : 'yyyy-mm-dd hh:ii'
				    });
      			} else {
      				// Must detach old format dirst
      				$('.form_datetime').datetimepicker('remove');
      				$('.form_datetime').datetimepicker({
				        //language:  'fr',
				        weekStart: 1,
				        todayBtn:  1,
						autoclose: 1,
						todayHighlight: 1,
						startView: 2,
						minView: 2,
						forceParse: 0,
				        format : 'yyyy-mm-dd'
				    });
      			}
		    });
		});
		
		function popup_updateEvent(eventId) {
			if(eventId) {
				$.ajax({
					type : "get",
					url : "event/get.do?",
					data: {'eventId' : eventId},
					dataType: 'json',
					cache : false,
					async : true,
					success : function(obj) {
						if (obj) {
							var event = obj;
							$('#txt_eventId_update').val(event.eventId);
							$('#txt_title_update').val(event.title);
							$('#txt_location_update').val(event.location);
							$('#sel_allDay_update').val(event.isAllDay);
							if (event.isAllDay == '1') {
								$('#txt_startDate_update').val(event.startDate.substr(0, 10));
								$('#txt_endDate_update').val(event.endDate.substr(0, 10));
							} else {
								$('#txt_startDate_update').val(event.startDate.substr(0, 16));
								$('#txt_endDate_update').val(event.endDate.substr(0, 16));
							}
							$('#sel_category_update').val(event.category);
							$('#popup_updateEvent').modal('show');
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
			if(eventId) {
				$.ajax({
					type : "get",
					url : "event/get.do?",
					data: {'eventId' : eventId},
					dataType: 'json',
					cache : false,
					async : true,
					success : function(obj) {
						if (obj) {
							var event = obj;
							$('#lbl_eventId_delete').text(eventId);
							$('#lbl_eventDetails').text(': ' + event.title + ', ' + event.startDate);
							$('#popup_deleteEvent').modal('show');
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
					type : "post",
					url : "event/add.do?",
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
			var eventId = $('#txt_eventId_update').val();
			var title = $('#txt_title_update').val();
			var location = $('#txt_location_update').val();
			var isAllDay = $('#sel_allDay_update').val();
			var startDate = $('#txt_startDate_update').val();
			var endDate = $('#txt_endDate_update').val();
			var category = $('#sel_category_update').val();
			
			if (!startDate) {
				alert("Start Date cannot be empty!");
			} else if (!endDate) {
				alert("End Date cannot be empty!");
			} else {
				$.ajax({
					type : "post",
					url : "event/update.do?",
					data: {'eventId' : eventId,
						'title' : title, 
						'location' : location,
						'isAllDay' : isAllDay, 
						'startDate' : startDate,
						'endDate' : endDate,
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
		
		function deleteEvent() {
			var eventId = $('#lbl_eventId_delete').text();
			if(eventId) {
				$.ajax({
					type : "post",
					url : "event/delete.do?",
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