<%@ page language="java" contentType="text/html" %>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title><c:out value="${pageView.title}"></c:out></title>
</head>
<body>
	<h1><c:out value="${pageView.pageTitle}" /></h1>
	
	<section>
		<h2><c:out value="${pageView.subPageTitle}" /></h2>
		<div><c:out value="${pageView.message}"></c:out></div>
	</section>

</body>
</html>