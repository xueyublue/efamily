<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + 
			":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%= basePath%>" />
<meta charset="utf-8" />
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>班级学生管理系统</title>
<style type="text/css">
	* { font-family: 微软雅黑; }
	.table-hover > tbody > tr:hover { background-color: lightyellow; }
	.table { margin-left:20px; }
	.ml { margin-left: 10px; }
</style>
</head>
<body>
	<div style="width:450px;">
		<table class="table table-hover" id="myTab">
			<caption style="font-size:24px;">班级信息</caption>
			<thead>
				<tr>
					<th>班级名称</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${classList}" var="c">
				<tr>
					<td><button class="btn btn-link" onclick="showClassMember(${c.id}, 1)">${c.name}</button></td>
					<td>${c.formattedCreateTime}</td>
					<td><button class="btn btn-info" onclick="delClass(${c.id}, this)">删除</button></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div>
		<button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal">创建班级</button>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">创建班级</h4>
					</div>
					<div class="modal-body">
						<label for="name">班级名称: </label>
						<input type="text" id="name" placeholder="请输入班级名称..." />
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-info" onclick="createClass()">创建</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="tableArea" style="width:720px; display:none;">
		<div style="height:350px;">
			<table class="table table-hover" id="myStudentTab">
				<caption style="font-size:24px;">学生信息</caption>
				<thead>
					<tr>
						<th>学号</th>
						<th>姓名</th>
						<th>性别</th>
						<th>电话</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<div style="margin-left:100px;">
			<button class="btn btn-info" id="btnStart">首页</button>
			<button class="btn btn-info" id="btnPrev">上一页</button>
			<button class="btn btn-info" id="btnNext">下一页</button>
			<button class="btn btn-info" id="btnEnd">末页</button>
		</div>
		<div class="modal fade" id="thyModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">修改学生信息</h4>
					</div>
					<div class="modal-body">
						<p>
							<label for="name">姓名: </label>
							<input type="text" id="stuname" value="" />
						</p>
						<p>
							<label for="male">性别: </label>
							<input type="radio" name="gender" value="true" />男
							<input type="radio" name="gender" value="false" />女
						</p>
						<p>
							<label for="tel">电话: </label>
							<input type="text" id="tel" value="" />
						</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" onclick="updateStudent()">修改</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript">
		var currentStudent;
		
		function showUpdateStudent(id, elem) {
			$.ajax({
				type: 'get',
				url: 'stu/getStudent.do',
				data: {'id': id},
				dataType: 'json',
				success: function(obj) {
					if(obj) {
						currentStudent = obj;
						$('#thyModal').modal('show');
						$('#stuname').val(currentStudent.name);
						$('input[name=gender]')[currentStudent.gender? 0 : 1].checked = true;
						$('#tel').val(currentStudent.tel);
						currentStudent.target = $(elem).parent().parent();
					}
				}
			});
		}
		
		function updateStudent() {
			if(currentStudent) {
				var tr = currentStudent.target;
				var name = $('#stuname').val();
				var gender =  $('input[name=gender]')[0].checked;
				var tel = $('#tel').val();
				$.ajax({
					type: 'get',
					url: 'stu/updateStudent.do',
					data: {'stu.id': currentStudent.id, 'stu.name': name, 'stu.gender': gender, 'stu.tel': tel},
					dataType: 'json',
					success: function(obj) {
						if(obj.done) {
							var nodes = tr.children();
							$(nodes[1]).text(name);
							$(nodes[2]).text(gender? "男" : "女");
							$(nodes[3]).text(tel);
						}
						$('#thyModal').modal('hide');
					}
				});
			}
		}
	
		function showClassMember(id, page) {
			$.ajax({
				type: 'get',
				url: 'stu/showStudent.do',
				data: {'classId': id, 'page': page},
				dataType: 'json',
				success: function(obj) {
					$('#tableArea').css('display', 'block');
					$('#myStudentTab tbody').empty();
					var array = obj.data;
					if(array.length == 0) {
						$('#tableArea').css('display', 'none');
					}
					else {
						for(var i = 0; i < array.length; i++) {
							var tempStu = array[i];
							var tr = $("<tr>");
							tr.append($("<td>" + tempStu.id + "</td>"));
							tr.append($("<td>" + tempStu.name + "</td>"));
							tr.append($("<td>" + (tempStu.gender? "男" : "女") + "</td>"));
							tr.append($("<td>" + tempStu.tel + "</td>"));
							tr.append($("<td><button class='btn btn-info' onclick='delStudent(" + tempStu.id + ", " + tempStu.myClass.id + ", " + page + ")'>删除</button><button class='btn btn-info ml' onclick='showUpdateStudent(" + tempStu.id + ", this)'>修改</button></td>"));
							$('#myStudentTab').append(tr);
						}
						$('#btnStart').attr('onclick', 'showClassMember(' + id + ", 1)").removeAttr('disabled');
						$('#btnPrev').attr('onclick', 'showClassMember(' + id + ", " + (obj.currentPage - 1) +")").removeAttr('disabled');
						$('#btnNext').attr('onclick', 'showClassMember(' + id + ", " + (obj.currentPage + 1) +")").removeAttr('disabled');
						$('#btnEnd').attr('onclick', 'showClassMember(' + id + ", " + obj.totalPage + ")").removeAttr('disabled');
						
						if(obj.currentPage == 1) {
							$('#btnStart').attr('disabled', 'disabled');
							$('#btnPrev').attr('disabled', 'disabled');
						}
						if(obj.currentPage == obj.totalPage) {
							$('#btnEnd').attr('disabled', 'disabled');
							$('#btnNext').attr('disabled', 'disabled');
						}
					}
				}
			});
		}
		
		function delStudent(id, classId, page) {
			if(confirm("确定删除此学生?")) {
				$.getJSON('stu/delete.do', {'stu.id': id}, function(obj) {
					if(obj.done) {
						showClassMember(classId, page);
					}
				});
			}
		}
	
		function createClass() {
			$.ajax({
				type: 'get',
				url: 'cls/createClass.do',
				data: {'name': $('#name').val()},
				dataType: 'json',
				success: function(obj) {
					if(obj) {
						var tr = $('<tr>');
						tr.append($('<td><button class="btn btn-link" onclick="showClassMember(' + obj.id + ', 1)">' + obj.name + '</button></td>'))
						tr.append($('<td>' + obj.createTime + '</td>'));
						tr.append($('<td><button class="btn btn-info" onclick="delClass(' + obj.id + ', this)">删除</button></td>'));
						$('#myTab').append(tr);
					}
					else {
						window.alert("添加失败!");
					}
				}
			});
			$('#myModal').modal('hide');
		}
	
		function delClass(id, elem) {
			if(confirm("确定删除此班级?")) {
				$.ajax({
					type: 'get',
					url: 'cls/DeleteClass.do',
					data: {'id': id},
					dataType: 'json',
					success: function(obj) {
						if(obj.done) {
							$(elem).parent().parent().remove();
						}
						else {
							window.alert("删除失败!");
						}
					}
				});
			}
		}
	</script>
</body>
</html>