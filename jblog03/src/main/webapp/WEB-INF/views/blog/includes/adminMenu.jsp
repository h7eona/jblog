<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<ul class="admin-menu">
	<li class="selected"><a href="${pageContext.request.contextPath}/${blog.id}/admin/default">기본설정</a></li>
	<li><a href="${pageContext.request.contextPath}/${blog.id}/admin/category">카테고리</a></li>
	<li><a href="${pageContext.request.contextPath}/${blog.id}/admin/write">글작성</a></li>
</ul>