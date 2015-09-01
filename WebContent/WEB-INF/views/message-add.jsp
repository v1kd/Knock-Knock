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
		<h2>Message Details</h2>
		
		<form:form method="POST" name="create-message" 
			commandName="messageAddBean">
			
			<c:if test="${pageView.valid == false }">
				<div><c:out value="${pageView.message }"></c:out> </div>
			</c:if>
			
			<form:errors></form:errors>
			
			<table>
			    <tr>
			        <td><form:label path="title">Title</form:label></td>
			        <td><form:input path="title" /></td>
			    </tr>
			    <tr>
			        <td><form:label path="message">Message</form:label></td>
			        <td><form:textarea path="message"/> </td>  
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