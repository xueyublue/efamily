<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
	    	
	    	<!-- Main Navigation Bar -->
	        <ul class="nav navbar-nav">
	           <!-- Home -->
	           <li id="home"><a href="home.do"><span class="glyphicon glyphicon-home"></span> Home</a></li>
	           
	           <!-- Family Share -->
	           <li id="family-share" class="dropdown">
	                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-cloud"></span> Family Share<b class="caret"></b></a>
	                <ul class="dropdown-menu">
	                    <li><a href="calendar.do">Calendar</a></li>
	                	</ul>
	            	</li>
	            
	            <!-- Administrator -->
	            <li id="administrator" class="dropdown">
	                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> Administrator<b class="caret"></b></a>
	                <ul class="dropdown-menu">
	                    <li><a href="user.do">User</a></li>
	                    <li><a href="#">Role</a></li>
	                    <li class="divider"></li>
	                    <li><a href="#">Log</a></li>
	                	</ul>
	            	</li>
	            
	            <!-- Help -->
	            <li id="help" class="dropdown">
	                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-info-sign"></span> Help<b class="caret"></b></a>
	                <ul class="dropdown-menu">
	                    <li><a href="#">Contact Me</a></li>
	                    <li><a href="#">Version History</a></li>
	                    <li class="divider"></li>
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
	                	<span class="text-primary" id="userName"></span>
	                	</a>
	                <ul class="dropdown-menu">
	                    <li><a href="#">Profile</a></li>
	                    <li><a href="#">Change Password</a></li>
	                    <li class="divider"></li>
	                    <li><a href="auth/logout.do"><span class="text-danger"><strong>Logout</strong></span></a></li>
	                	</ul>
	            	</li>
	         </ul>
	    	</div>
    	</div>
</nav>