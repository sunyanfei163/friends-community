<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" 
			+ request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<meta name="description"
	content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap-3.3.7-dist/css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/simple-line-icons.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
</head>
<body style="height: 100%;" background="${pageContext.request.contextPath}/images/login/bodybg.jpg">
	<section id="content" class="m-t-lg wrapper-md animated fadeInUp ">
	<div class="container aside-xl" style="margin-top: 48px;">
		<a class="navbar-brand block"><span class="h1 font-bold"
			style="color: #ffffff">登录</span></a><br/>
		<section class="m-b-lg">
		<form action='<c:url value="/login.html"/>' method="post">
			<div class="form-group">
				<input type="username" name="userName" placeholder="用户名"
					class="form-control  input-lg text-center no-border">
			</div>
			<div class="form-group">
				<input type="password" name="password" placeholder="密码"
					class="form-control  input-lg text-center no-border">
			</div>
			<div class="form-group">
				<table>
					<tr><td>
							<input type="text" name="validateCode" placeholder="验证码"
							class="form-control  input-lg text-center no-border">
						</td>
						<td>
							<img id="validateCodeImg" src="<%=basePath%>/validateCode.html" 
								onclick="javascript:reloadValidateCode();" />
						</td>
					</tr>
				</table>
			</div>
			<c:if test="${ !empty errorMsg_validateCode }">
				<div class="form-group">
					<label style="color: red;">${ errorMsg_validateCode}</label>
				</div>
			</c:if>
			<button type="submit"
				class="btn btn-lg btn-danger lt b-white b-2x btn-block"
				id="validate-submit">
				<i class="icon-arrow-right pull-right"></i><span class="m-r-n-lg">登录</span>
			</button>
			<a class="btn btn-lg btn-danger lt b-white b-2x btn-block" href="<%=basePath%>/registerPage.html">
				<i class="icon-arrow-right pull-right"></i><span class="m-r-n-lg">注册</span>
			</a>
		</form>
		</section>
	</div>
	</section>
</body>
<script>
	function reloadValidateCode(){
		$('#validateCodeImg').attr("src", "<%=basePath%>/validateCode.html?data=" + new Date() + Math.floor(Math.random()*24));
	}
</script>
</html>