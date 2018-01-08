<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>E-Family</title>
<link href="static/bootstrap/css/bootstrap.css" rel="stylesheet">
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
				<li><a href="" class="active">Home</a></li>
			</ol>
		</div>
	</div>
	
	<!-- Notification Bar -->
	<div class="alert alert-success alert-dismissable fade in hide" id="div_notif">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<strong id="txt_notif_strong"></strong>  <span id="txt_notif_normal">Indicates a successful or positive action.</span>
	</div>
	
	<div>
		<p>Hi <strong style="color: purple;"><%=session.getAttribute("USER_NAME")%></strong>, 
			welcome to E-Family. 
			Current system date/time is <span class="text-primary" id="txt_sysDateTime"></span>.</p>
	</div>

	<!-- End of Container -->
	</div>
	
	<script src="static/js/jquery/jquery.min.js"></script>
	<script src="static/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="static/js/common-methods.js"></script>
	
	<!-- Customize Scripts -->
	<script type="text/javascript">
		$(document).ready(function() {
			document.getElementById("userName").innerHTML = '<%=session.getAttribute("USER_NAME")%>';
			
			$('#home').addClass('active');

			setInterval('setSysDateTime()', 100);
		});
		
		function setSysDateTime() {
			$('#txt_sysDateTime').text(dateFormatter("yyyy-MM-dd hh:mm:ss"));
		}

		function setNotification(type, title, content) {
			if (type == 'alert-success') {
				$('#div_notif').removeClass('alert-danger');
				$('#div_notif').addClass('alert-success');	
			} else if (type == 'alert-danger') {
				$('#div_notif').removeClass('alert-success');
				$('#div_notif').addClass('alert-danger');	
			}
			$('#div_notif').removeClass('hide');
			$('#txt_notif_strong').text(title);
			$('#txt_notif_normal').text(content);
			scrollToTop();
		}
	</script>
</body>
</html>