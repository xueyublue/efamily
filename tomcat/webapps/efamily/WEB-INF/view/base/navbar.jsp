<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<div class="row" style="margin-left: 0px; margin-right: 0px;">
	<div class="col-sm-12" 
		style=" 
			background-color: #191970; 
			color: #FFFAFA;
			background: -webkit-linear-gradient(left, rgba(25,25,112,1), rgba(25,25,112,0.4));
			background: -o-linear-gradient(right, rgba(25,25,112,1), rgba(25,25,112,0.4));
			background: -moz-linear-gradient(right, rgba(25,25,112,1), rgba(25,25,112,0.4));
			background: linear-gradient(to right, rgba(25,25,112,1), rgba(25,25,112,0.4));
	">
		<strong style="font-family: Arial; opacity: 0.95;">Family Information Share System 1.0.0</strong>
		<!-- <span style="font-family: Arial; font-size: 12px; opacity: 0.9;">released on <span style="font-size: 11px;">01-12-2017</span></span> -->
	</div>
</div>
<nav class="navbar navbar-default navbar-static-top">
    <div class="container-fluid">
	    <div class="navbar-header">
	    	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#efamily-navbar" aria-expanded="false">
	    		<span class="sr-only">Navigation</span>
	    		<span class="icon-bar"></span>
	    		<span class="icon-bar"></span>
	    		<span class="icon-bar"></span>
	    		</button>
	        <a class="navbar-brand"><span class="h4"><strong><span style="color: purple; opacity: 0.8;">E-Family</span></strong></span></a>
	    </div>
	    <div class="collapse navbar-collapse" id="efamily-navbar">
	    	
	    	<!-- Main Navigation Bar -->
	        <ul class="nav navbar-nav">
	           <!-- Home -->
	           <li id="home"><a href="home.do"><span class="glyphicon glyphicon-home"></span> <strong>Home</strong></a></li>
	           
	           <!-- Family Share -->
	           <li id="family-share" class="dropdown">
	                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-ice-lolly-tasted"></span> <strong>Family Share</strong><b class="caret"></b></a>
	                <ul class="dropdown-menu">
	                    <li><a href="event.do"><span class="glyphicon glyphicon-calendar"></span> Event</a></li>
	                    <li><a href="cloud.do"><span class="glyphicon glyphicon-cloud"></span> Cloud</a></li>
	                </ul>
	            </li>
	            
	            <!-- Administrator -->
	            <li id="administrator" class="dropdown">
	                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-console"></span> <strong>Administrator</strong><b class="caret"></b></a>
	                <ul class="dropdown-menu">
	                    <li><a href="user.do"><span class="glyphicon glyphicon-user"></span> User</a></li>
	                    <li><a href="role.do"><span class="glyphicon glyphicon-glass"></span> Role</a></li>
	                </ul>
	            </li>
	            
	            <!-- Help -->
	            <li id="help" class="dropdown hide">
	                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-info-sign"></span> <strong>Help</strong><b class="caret"></b></a>
	                <ul class="dropdown-menu">
	                    <li><a href="#">Contact Me</a></li>
	                    <li><a href="#">About E-Family</a></li>
	                </ul>
	            </li>
	        </ul>
	        
	        <!-- Current Login User -->
	        <ul class="nav navbar-nav navbar-right">
	         	<li id="loginuser" class="dropdown">
	                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
	                	<!-- img class="image-responsive" height="18" src="static/img/user_24_24.png" /-->
	                	<span class="glyphicon glyphicon-user"></span>
	                	<strong><span class="text-primary" id="userName"></span></strong>
	                	</a>
	                <ul class="dropdown-menu">
	                    <li><a href="#" class="hide">Profile</a></li>
	                    <li><a href="#" class="hide">Change Password</a></li>
	                    <li class="divider hide"></li>
	                    <li><a href="logout.do"><span class="text-danger"><strong>Logout</strong></span></a></li>
	                </ul>
	            </li>
	         </ul>
	    	</div>
    	</div>
</nav>