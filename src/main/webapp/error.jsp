<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<title>Error</title>
	</head>
	<body>
		<h4>The application has malfunctioned.</h4>
		<p>Please contact technical support with the following information:</p>

		<!-- the exception and exceptionStack bean properties were created by
		the Struts2 Exception Interpreter -->
		<h4>Exception name: <s:property value="exception" /></h4>
		<h4>Exception Details: <s:property value="exceptionStack" /></h4>

		<p><a href="index.jsp">Return to the home page.</a></p>
	</body>
</html>
