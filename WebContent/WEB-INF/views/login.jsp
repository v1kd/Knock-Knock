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
		<h2>User login</h2>
		
		<form:form method="POST" name="login" commandName="loginFormBean" action="loginAuth">
		
			<c:if test="${pageView.valid == false }">
				<div><c:out value="${pageView.message }"></c:out> </div>
			</c:if>
		
			<table>
			    <tr>
			        <td><form:label path="username">Username</form:label></td>
			        <td><form:input path="username" /></td>
			    </tr>
			    <tr>
			        <td><form:label path="password">Password</form:label></td>
			        <td><form:input path="password" type="password" /></td>
			    </tr>
			    
			    <tr>
			        <td colspan="2">
			            <input type="submit" value="Submit" name="submit" />
			        </td>
			    </tr>
			</table>
		</form:form>
	</section>

</body>
</html>