<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login to E-Family</title>
<link href="static/bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<div class="container">
	<div class="row">
		<form class="col-xs-12 col-sm-6 col-sm-offset-3" style="margin-top: 10px;">
			<fieldset>
				<legend><strong><span style="color: purple;">Login to E-Family</span></strong></legend>
				<div class="form-group">
					<label>User Id</label>
					<input type="text" class="form-control" placeholder="User Id" style="font-weight: bold; color: purple;" id="txt_userId"/>
				</div>
				<div class="form-group">
					<label>Password</label>
					<input type="password" class="form-control" placeholder="Password" style="font-weight: bold; color: purple;" id="txt_password"/>
				</div>
				<div class="checkbox">
					<label><input type="checkbox" checked="true" id="chk_remPassword"/>Remember Password</label>
				</div>
				<button type="button" class="btn btn-primary col-xs-12" style="margin-top: 10px;" onclick="login()" id="btn_login"><strong>Login</strong></button>
				<strong><span id="txt_result" class="col-xs-12 text-danger" style="margin-top: 10px;"></span></strong>
			</fieldset>
		</form>
	</div>
</div>
	
	<script src="static/js/jquery/jquery.min.js"></script>
	<script src="static/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="static/js/common-methods.js"></script>
	
	<!-- Customize Scripts -->
	<script type="text/javascript">
		$(document).ready(function() {
			if (getCookie('efamily_userId') && getCookie('efamily_password')) {
				$('#txt_userId').val(getCookie('efamily_userId'));
				$('#txt_password').val(getCookie('efamily_password'));
				$('#btn_login').focus();
			} else {
				$('#txt_userId').focus();
			}

			$('#chk_remPassword').onchange = function(){
	            if(!this.checked){
	                delCookie('efamily_userId');
	                delCookie('efamily_password');
	            }
	        };
		});

		function login() {
			$('#btn_login').attr('disabled', "true");

			// Get values
			var userId = $('#txt_userId').val();
			var password = $('#txt_password').val();
			var remPassword = $('#chk_remPassword').is(':checked');

			// Validate values
			if (!userId) {
				$('#txt_result').text("User Id cannot be empty!");
				$('txt_userId').focus();
				setTimeout(function() {
					$('#txt_userId').focus();
				}, 500);
			} else if (!password) {
				$('#txt_result').text("Password cannot be empty!");
				$('txt_password').focus();
				setTimeout(function() {
					$('#txt_password').focus();
				}, 500);
			} else {
				// Call AJAX
				$.ajax({
					type : "post",
					url : "login.do?" 
							+ "userId=" + userId
							+ "&password=" + password,
					cache : false,
					async : true,
					success : function(obj) {
						if (remPassword) {
							setCookie('efamily_userId', userId, 7);
							setCookie('efamily_password', password, 7);	
						} else {
							delCookie('efamily_userId');
	                		delCookie('efamily_password');
						}

						window.location.href = "home.do";
					},
					error : function(obj) {
						$('#txt_result').text(obj.responseText.replace('"', '').replace('"', ''));
						$('#btn_login').removeAttr('disabled');
					}
				});
			}
		}
	</script>
</body>
</html>