<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">
<title>注册</title>
</head>
<body>
	<form action="/">
		<div class="form-group">
			<label for="userName">用户名</label> <input
				type="text" class="form-control" id="userName" name="userName"
				placeholder="用户名">
		</div>
		<div class="form-group">
			<label for="password">密码</label> <input
				type="password" class="form-control" id="password" name="password"
				placeholder="密码">
		</div>
		<div class="form-group">
			<label for="passwordValid">确认密码</label> <input
				type="password" class="form-control" id="passwordValid" name="passwordValid"
				placeholder="确认密码">
		</div>
		<c:if test="${empty errorMsg}">
			<div class="form-group">
				<label for="passwordValid" style="color: red;">${errorMsg}</label>
			</div>
		</c:if>
		<button type="submit" class="btn btn-default">Submit</button>
	</form>
</body>
</html>