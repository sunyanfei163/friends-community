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
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap-3.3.7-dist/css/bootstrapValidator.min.css">
<title>注册</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-3.3.7-dist/js/bootstrapValidator.min.js"></script>
</head>
<body>
	<form action='<c:url value="/register.html"/>' class="center-block" style="width: 350px;">
		<h1>注册</h1>
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
		<c:if test="${!empty errorMsg}">
			<div class="form-group">
				<label for="passwordValid" style="color: red;">${errorMsg}</label>
			</div>
		</c:if>
		<button type="submit" class="btn btn-default">Submit</button>
	</form>
</body>
<script type="text/javascript">
	$(function(){
		$('form').bootstrapValidator({
			message:'This value is not valid',
			feedbackIcons:{
				valid:'glyphicon glyphicon-ok',
				invalid:'glyohicon glyphicon-remove',
				validating:'glyohicon glyphicon-refesh'
			},
			fields:{
				userName:{
					message:'用户名验证失败',
					validators:{
						notEmpty:{
							message:'用户名不能为空'
						}
					}
				},
				password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        }
                    }
                },
                passwordValid: {
                    validators: {
                        notEmpty: {
                            message: '确认密码不能为空'
                        }
                    }
                }
			}
		})
	});
</script>
</html>