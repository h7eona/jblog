<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="navigation">
	<h2>카테고리</h2>
	<ul>
		<c:forEach items="${categories}" var="category">
			<li><a href="${pageContext.request.contextPath}/${blog.id}/${category.id}">${category.name}</a></li>
		</c:forEach>
	</ul>
</div>