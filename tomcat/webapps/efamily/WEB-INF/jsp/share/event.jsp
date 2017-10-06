<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>E-Family</title>
<link href="static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
	<!-- Navigation Bar -->
	<nav class="navbar navbar-default" role="navigation">
	    <div class="container-fluid">
		    <div class="navbar-header">
		        <a class="navbar-brand"><span class="h4">E-Family</span></a>
		    	</div>
		    <div>
		        <ul class="nav navbar-nav">
		           <li><a href="home.do">Home</a></li>
		           <li class="dropdown active">
		                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Family Share<b class="caret"></b></a>
		                <ul class="dropdown-menu">
		                    <li><a href="event.do">Event</a></li>
		                	</ul>
		            	</li>
		            <li class="dropdown">
		                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Administrator<b class="caret"></b></a>
		                <ul class="dropdown-menu">
		                    <li><a href="user.do">User</a></li>
		                    <li><a href="#">Role</a></li>
		                    <li class="divider"></li>
		                    <li><a href="#">Log</a></li>
		                	</ul>
		            	</li>
		            <li class="dropdown">
		                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Help<b class="caret"></b></a>
		                <ul class="dropdown-menu">
		                    <li><a href="#">Contact Me</a></li>
		                    <li><a href="#">Version History</a></li>
		                    <li class="divider"></li>
		                    <li><a href="#">About E-Family</a></li>
		                	</ul>
		            	</li>
		            <li class="dropdown">
		                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span id="userName"></span><b class="caret"></b></a>
		                <ul class="dropdown-menu">
		                    <li><a href="#">Profile</a></li>
		                    <li><a href="#">Change Password</a></li>
		                    <li class="divider"></li>
		                    <li><a href="auth/logout.do">Logout</a></li>
		                	</ul>
		            	</li>
		        </ul>
		    	</div>
	    	</div>
	</nav>
	<!-- Page Content -->
	
	</div>
	<script src="static/js/jquery/jquery.min.js"></script>
	<script src="static/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			document.getElementById("userName").innerHTML = '<%=session.getAttribute("userName")%>';
		});
</script>
</body>
</html>