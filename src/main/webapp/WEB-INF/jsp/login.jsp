<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<meta name="description"
	content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/simple-line-icons.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css" type="text/css" />
</head>
<body background="/image/login/bodybg.jpg">
	<section id="content" class="m-t-lg wrapper-md animated fadeInUp ">
	<div class="container aside-xl" style="margin-top: 48px;">
		<a class="navbar-brand block"><span class="h1 font-bold"
			style="color: #ffffff">登录</span></a>
		<section class="m-b-lg">
		<form action="javascript:;" method="post">
			<div class="form-group">
				<input type="username" name="username" placeholder="用户名"
					class="form-control  input-lg text-center no-border">
			</div>
			<div class="form-group">
				<input type="password" name="password" placeholder="密码"
					class="form-control  input-lg text-center no-border">
			</div>

			<button type="submit"
				class="btn btn-lg btn-danger lt b-white b-2x btn-block"
				id="validate-submit">
				<i class="icon-arrow-right pull-right"></i><span class="m-r-n-lg">登录</span>
			</button>
		</form>
		</section>
	</div>
	</section>
	<!-- footer -->
	<footer id="footer">
	<div class="text-center padder"></div>
	</footer>
</body>
</html>