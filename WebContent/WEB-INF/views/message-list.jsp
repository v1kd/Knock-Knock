<%@ page language="java" contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title><c:out value="${pageView.title}"></c:out></title>
</head>
<body>
	<h1><c:out value="${pageView.pageTitle}"></c:out></h1>
	
	<section>
		<h2>Messages list</h2>
		<div>
			<c:forEach items="${messages}" var="message">
				<div class="message">
					<div><c:out value="${message.title}" /></div>
					<div><c:out value="${message.message }" /> </div>
				</div>
			</c:forEach>
		</div>
	</section>

</body>
</html>