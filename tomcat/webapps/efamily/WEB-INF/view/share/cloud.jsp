<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cloud</title>
<link href="static/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
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
			<ol class="breadcrumb" style="margin-top: 0px;">
				<li>Family Share</li>
				<li><a href="" class="active">Cloud</a></li>
			</ol>
		</div>
	</div>

	<!-- Dashboard -->
	<div class="row">
		<div class="col-xs-12">
			<ol class="breadcrumb" style="margin-top: 0px;">
				<li><strong>Dashboard</strong></li>
			</ol>
		</div>
	</div>
	<div style="margin-bottom: 10px;">
		10,123 files found, 67% storage used.
	</div>

	<!-- Cloud -->
	<div class="row">
		<div class="col-xs-12">
			<ol class="breadcrumb" style="margin-top: 0px;" id="list_path">
				<li><a href="cloud.do"><strong>Cloud</strong></a></li>
			</ol>
		</div>
	</div>

	<!-- Buttons -->
	<div class="row">
		<div class="col-xs-12">
			<button class="btn btn-sm btn-default" id="btn_add"><span class="glyphicon glyphicon-plus"></span>Add</button>
			<button class="btn btn-sm btn-primary" onclick="btnUploadClicked()" id="btn_upload"><span class="glyphicon glyphicon-upload"></span>Upload</button>
			<button class="btn btn-sm btn-primary" id="btn_download"><span class="glyphicon glyphicon-download"></span>Download</button>
			<button class="btn btn-sm btn-warning" id="btn_rename"><span class="glyphicon glyphicon-pencil"></span>Rename</button>
			<button class="btn btn-sm btn-danger" id="btn_delete"><span class="glyphicon glyphicon-remove"></span>Delete</button>
			<input  class="hide" type="file" id="file_upload" />
		</div>
	</div>

	<!-- Upload File List -->
	<div class="row">
		<div class="col-xs-12">
			<div class="table-responsive">
				<table class="table table-striped table-hover hide" id="table_uploadFiles">
					<thead>
						<tr>
							<th>Name</th>
							<th>Size</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>	
		</div>
	</div>

	<!-- File List -->
	<div class="row">
		<div class="col-xs-12">
			<div class="table-responsive">
				<table class="table table-striped table-hover" id="table_cloudFiles">
					<thead>
						<tr>
							<th>Select</th>
							<th>Name</th>
							<th>Date Modified</th>
							<th>Size</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>	
		</div>
	</div>

	<!-- Notification Bar -->
	<div class="alert alert-success alert-dismissable fade in hide" id="div_notif">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<strong id="txt_notif_strong"></strong>  <span id="txt_notif_normal">Indicates a successful or positive action.</span>
	</div>

	<!-- End of Container -->
</div>
	
	<script type="text/javascript" src="static/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="static/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="static/datetimepicker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<script type="text/javascript" src="static/datetimepicker/js/locales/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
	<script type="text/javascript" src="static/js/common-methods.js"></script>

	<!-- Customize Scripts -->
	<script type="text/javascript" language="JavaScript">

		var currentPath;

		$(document).ready(function() {
			document.getElementById("userName").innerHTML = '<%=session.getAttribute("USER_NAME")%>';
			
			$('#family-share').addClass('active');

			$('#btn_add').removeAttr('disabled');
			$('#btn_download').attr('disabled', 'true');
			$('#btn_upload').attr('disabled', 'true');
			$('#btn_rename').attr('disabled', 'true');
			$('#btn_delete').attr('disabled', 'true');

			getSubFolders('/');

			currentPath = "/";

			$('#file_upload').on('change', function() {
				var selectedFile = document.getElementById('file_upload');
				if ('files' in selectedFile && selectedFile.files.length != 0) {
					var formData = new FormData(selectedFile.files[0]);

					$('#table_uploadFiles tbody').empty();
					var tr = $("<tr>");
					tr.append($('<td>' + selectedFile.files[0].name + '</td>'));
					tr.append($('<td width="120">' + fileSizeToDisp(selectedFile.files[0].size) + '</td>'));
					tr.append($('<td width="160">'
						+ '<button class="btn btn-xs btn-primary" onclick="upload(' + "'" + formData + "'" + ')" id="btn_upload_AJAX"><span class="glyphicon glyphicon-upload"></span></button>'
						+ ' <button class="btn btn-xs btn-danger" id="btn_delete_AJAX"><span class="glyphicon glyphicon-remove"></span></button></td>'));
					tr.append("</tr>");

					$('#table_uploadFiles').append(tr);

					$('#table_uploadFiles').removeClass('hide');
				}
			});
		});

		function upload(formData) {
			// var form = new FormData();
			// form.append("file", document.getElementById('file_upload').files[0]);
			// Call AJAX
			$.ajax({
				type : "post",
				url : "cloud/upload.do?",
				data : formData,
				processData : false,
				cache : false,
				async : false,
				contentType : false,
				success : function(obj) {
					// TODO:
					alert('Uploaded.');
				},
				error : function(obj) {
					if (obj.status == '901') {
						window.location.href = "login.do";
					} else {
						var response = obj.responseText.replace('"', '').replace('"', '');
						setNotification('alert-danger', 'Server >', response);
					}
				}
			});
		}
		
		function btnUploadClicked() {
			$('#file_upload').trigger("click");
		}

		function getSubFolders(path) {

			// Call AJAX
			$.ajax({
				type : "get",
				url : "cloud/getSubFiles.do?",
				data: {'path' : path},
				dataType: 'json',
				cache : false,
				async : true,
				success : function(obj) {
					// TODO: Set current path
					// if (!endWith(path, "/")) {
					// 	$('#list_path').append('<li><a href="#">' + path +'</a></li>');
					// }

					$('#table_cloudFiles tbody').empty();

					for(var i = 0; i < obj.length; i++) {
						var tr = $("<tr>");
						tr.append($('<td width="40" align="center"><input type="checkbox"></td>'));
						if (obj[i]['isDir']) {
							var filePath = "";
							if (endWith(path, "/")) {
								filePath = path + obj[i]['name'];
							} else {
								filePath = path + "/" + obj[i]['name'];
							}
							tr.append($('<td><span class="glyphicon glyphicon-folder-open"></span> <a href="javascript:void(0);" onclick="getSubFolders(' + "'" + filePath + "'" + ')">' + obj[i]['name'] + "</a></td>"));
						} else {
							tr.append($('<td><span class="glyphicon glyphicon-file"></span> ' + obj[i]['name'] + "</td>"));
						}
						tr.append($('<td width="160">' + obj[i]['lastModifiedDate'] + "</td>"));
						tr.append($('<td width="120">' + obj[i]['size'] + "</td>"));
						tr.append("</tr>");
						$('#table_cloudFiles').append(tr);
					}

					$('#btn_upload').removeAttr('disabled');
				},
				error : function(obj) {
					if (obj.status == '901') {
						window.location.href = "login.do";
					} else {
						var response = obj.responseText.replace('"', '').replace('"', '');
						setNotification('alert-danger', 'Server >', response);
					}
				}
			});
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