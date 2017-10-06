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
	<nav class="navbar navbar-default navbar-static-top">
	    <div class="container-fluid">
		    <div class="navbar-header">
		    	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#efamily-navbar" aria-expanded="false">
		    		<span class="sr-only">Navigation</span>
		    		<span class="icon-bar"></span>
		    		<span class="icon-bar"></span>
		    		<span class="icon-bar"></span>
		    		</button>
		        <a class="navbar-brand"><span class="h4">E-Family</span></a>
		    	</div>
		    <div class="collapse navbar-collapse" id="efamily-navbar">
		        <ul class="nav navbar-nav">
		           <li class="active"><a href="home.do">Home</a></li>
		           <li class="dropdown">
		                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Family Share<b class="caret"></b></a>
		                <ul class="dropdown-menu">
		                    <li><a href="calendar.do">Calendar</a></li>
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
		        </ul>
		         <ul class="nav navbar-nav navbar-right">
		         	<li class="dropdown">
		                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
		                	<!-- img class="image-responsive" height="18" src="static/img/user_24_24.png" /-->
		                	<span class="glyphicon glyphicon-user"></span>
		                	<label id="userName"></label>
		                	</a>
		                <ul class="dropdown-menu">
		                    <li><a href="#">Profile</a></li>
		                    <li><a href="#">Change Password</a></li>
		                    <li class="divider"></li>
		                    <li><a href="auth/logout.do"><span>Logout</span></a></li>
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