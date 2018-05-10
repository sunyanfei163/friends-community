<%@page import="com.sun.baisc.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String userName = ((User)session.getAttribute("currentUser")).getUserName();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>首页</title>
<meta name="keywords" content="侧边导航菜单(可分组折叠)">
<meta name="description" content="侧边导航菜单(可分组折叠)" />
<meta name="HandheldFriendly" content="True" />
<link rel="shortcut icon" href="img/favicon.ico">
<link href="${pageContext.request.contextPath }/js/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">

<style>
.panel-group {
	max-height: 770px;
	overflow: auto;
}

.leftMenu {
	margin: 10px;
	margin-top: 5px;
}

.leftMenu .panel-heading {
	font-size: 14px;
	padding-left: 20px;
	height: 36px;
	line-height: 36px;
	color: white;
	position: relative;
	cursor: pointer;
} /*转成手形图标*/
.leftMenu .panel-heading span {
	position: absolute;
	right: 10px;
	top: 12px;
}

.leftMenu .menu-item-left {
	padding: 2px;
	background: transparent;
	border: 1px solid transparent;
	border-radius: 6px;
}

.leftMenu .menu-item-left:hover {
	background: #C4E3F3;
	border: 1px solid #1E90FF;
}
</style>
</head>

<body>
	<div class="row">
		<div class="col-md-2">
			<div id="menu" class="panel-group table-responsive" role="tablist">
				<!-- <div class="panel panel-primary leftMenu">
					利用data-target指定要折叠的分组列表
					<div class="panel-heading" id="collapseListGroupHeading1"
						data-toggle="collapse" data-target="#collapseListGroup1"
						role="tab">
						<h4 class="panel-title">
							分组1 <span class="glyphicon glyphicon-chevron-up right"></span>
						</h4>
					</div>
					.panel-collapse和.collapse标明折叠元素 .in表示要显示出来
					<div id="collapseListGroup1" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="collapseListGroupHeading1">
						<ul class="list-group">
							<li class="list-group-item">
								利用data-target指定URL
								<button class="menu-item-left" data-target="test2.html">
									<span class="glyphicon glyphicon-triangle-right"></span>分组项1-1
								</button>
							</li>
						</ul>
					</div>
				</div> -->
			</div>
		</div>
		<div class="col-md-10">内容</div>
	</div>
	<script src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap-3.3.7-dist/js/bootstrap.min.js "></script>
	<script>
		$(function() {
			function childMenu(child,parentId,target){
				var htm = "<div id='collapseListGroup" + target + "' class='panel-collapse collapse in'" +
						"role='tabpanel' aria-labelledby='collapseListGroupHeading" + target + "'> " +
						"<ul class='list-group'>";
				for (var i = 0; i < child.length; i++) {
					htm += "<li class='list-group-item'>" +
							"<button class='menu-item-left' data-target='test2.html'>" +
								"<span class='glyphicon glyphicon-triangle-right'></span>" + child[i]["name"] +
							"</button>" +
							"</li>";
					
				}
				htm += "</ul></div>";
				$('#' + parentId).append(htm);
			}
			$.post('${pageContext.request.contextPath }/getMenu.html',
					{userName:'<%=userName%>'},
					function(data){
						for (var i = 0; i < data.length; i++) {
							$('#menu').append("<div class='panel panel-primary leftMenu' id='leftMenu" 
											+ data[i]["id"] + "'>" +
									"<div class='panel-heading' id='collapseListGroupHeading" + data[i]["id"] +"'" +
										"data-toggle='collapse' data-target='#collapseListGroup" + data[i]["id"] + "' role='tab'>" +
										"<h4 class='panel-title'>"+ data[i]["name"] +
											"<span class='glyphicon glyphicon-chevron-up right'></span>" +
										"</h4>" +
									"</div>");
							if(data[i].child != undefined && data[i].child != null){
								childMenu(data[i].child, "leftMenu" + data[i]["id"], data[i]["id"]);
							}
							$('#menu').append("</div>");
						}
					},
					"json"
			);
			$(".panel-heading").click(function(e) {
				/*切换折叠指示图标*/
				$(this).find("span").toggleClass("glyphicon-chevron-down");
				$(this).find("span").toggleClass("glyphicon-chevron-up");
			});
		});
	</script>
</body>

</html>