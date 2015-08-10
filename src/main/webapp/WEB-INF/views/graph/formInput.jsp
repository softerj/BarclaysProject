<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Airport Shortest Path</title>
</head>
<body>
 
	<center>
		<h2>Shortest Path With Dijkstra's Algorithm</h2>
		<form:form method="POST" commandName="formInput" action="graph/findPaths" modelAttribute="formInput">
			TestFile: <form:select path="fileId">
	        	<form:option value="" label="...." />
	            <form:options items="${testDataOptions}" itemValue="id" itemLabel="display" />
            </form:select>
            <br/>
            User: <form:input path="userName"/>
            <br/>
            <input type="submit" name="submit" value="Submit">    
            <br />
            <form:errors path="fileId" />
            <form:errors path="userName" />        
		</form:form>
	</center>
</body>
</html>