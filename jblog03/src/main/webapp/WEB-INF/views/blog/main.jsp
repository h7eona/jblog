<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("crcn", "\r\n");
	pageContext.setAttribute("br", "<br/>");
%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>JBlog</title>
	<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/blog/includes/header.jsp" />
		<div id="wrapper">
				<div id="content">
					<div class="blog-content">
						<c:choose>
						
							<c:when test="${not empty selectedPost}">
								<h4>${selectedPost.title}</h4>
								<p><c:out escapeXml="false" value="${fn:replace(selectedPost.contents, crcn, br)}"/></p>
							</c:when>
							
							<c:otherwise>
								<c:if test="${not empty posts}">
									<h4>${posts[0].title}</h4>
									<p><c:out escapeXml="false" value="${fn:replace(posts[0].contents, crcn, br)}"/></p>
								</c:if>
								
								<c:if test="${empty posts}">
									<p>포스트가 없습니다.</p>
								</c:if>
								
							</c:otherwise>
						</c:choose>
					</div>
		
					<ul class="blog-list">
						<c:forEach items="${posts}" var="post">
							<li>
								<a href="${pageContext.request.contextPath}/${blog.id}/${post.categoryId}/${post.id}">
										${post.title}
								</a>
								<span>
									<fmt:parseDate value="${post.regDate}" pattern="yyyy-MM-dd" var="formattedDate" />
									<fmt:formatDate value="${formattedDate}" pattern="yyyy-MM-dd" />
								</span>
							</li>
						</c:forEach>
					</ul>

				</div>
				
			<div id="extra">
				<div class="blog-profile">
					<img id="profile" src="${pageContext.request.contextPath}${blog.profile}">
				</div>
			</div>
			<c:import url="/WEB-INF/views/blog/includes/navigation.jsp" />
			<c:import url="/WEB-INF/views/blog/includes/footer.jsp" />
		</div>
	</div>
</body>
</html>