<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<form class="login-form" name="loginform" method="post" action="${pageContext.request.contextPath}/user/auth">
			<label for="id"><spring:message code="user.join.label.id"/></label>
			<input id="id" name="id" type="text" value="">
			
			<label for="password"><spring:message code="user.join.label.password"/></label>
			<input id="password" name="password" type="password" value="">
			<c:if test="${result == 'fail'}">
				<p>
					로그인이 실패 했습니다.
				</p>
			</c:if>
      		<input type="submit" value="로그인">
		</form>
	</div>
</body>
</html>
