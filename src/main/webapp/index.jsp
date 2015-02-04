<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<s:head />
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<title>TestApp- Welcome</title>
	</head>
	<body>
		<h1>Welcome to Struts2!</h1>

		<s:url action="createRegistration" var="newRegLink" />
		<p><a href="${newRegLink}">Register Now!</a></p>

		<h3>Debugging</h3>
		<p><a href='<s:url action="index" namespace="config-browser" />'>Launch the configuration browser</a></p>
		<s:url action="index" var="indexLink">
			<s:param name="debug">browser</s:param>
		</s:url>
		<p><a href="${indexLink}">Reload this page with debugging</a></p>

		<hr />
		<s:text name="contact" />
	</body>
</html>
