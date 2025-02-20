<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="header">
	<h1>${blog.title}</h1>
	<ul>
		<c:choose>
			<c:when test="${empty authUser}">
				<li><a href="${pageContext.request.contextPath}/">홈</a></li>
				<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
			</c:when>
			<c:otherwise>
				<c:if test="${authUser.id ne id}">
					<li><a href="${pageContext.request.contextPath}/">홈</a></li>
					<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				</c:if>
				<c:if test="${authUser.id eq id}">
					<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
					<li><a href="${pageContext.request.contextPath}/${id}/admin/default">블로그 관리</a></li>
					<li><a href="${pageContext.request.contextPath}/${id}">홈</a></li>
				</c:if>
			</c:otherwise>
		</c:choose>
	</ul>
</div>
