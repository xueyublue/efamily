<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Calendar</title>
<link href="static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
	<!-- Navigation Bar -->
	<jsp:include page="../navbar.jsp"></jsp:include>
	
	<!-- Page Content -->
	<p>Family Share > Calendar</p>
	
	</div>
	<script src="static/js/jquery/jquery.min.js"></script>
	<script src="static/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			document.getElementById("userName").innerHTML = '<%=session.getAttribute("userName")%>';
			$('#family-share').addClass('active');
		});
</script>
</body>
</html>