<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<s:head />
		<base href="<%=basePath%>">
		<title>Edit Person</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
	</head>
	<body>
		<s:if test="id > 0">
			<h3>Edit Person</h3>
		</s:if>
		<s:else>
			<h3>Create Person</h3>
		</s:else>
		<s:form action="saveOrUpdateRegistration" method="post">
			<s:token />
			<p><s:textfield key="person.firstName" label="First name" />
			<br />
			<s:textfield key="person.lastName" label="Last name" /> </p>
			<s:radio key="person.gender" list="allowedGenders" />
			<s:checkbox key="person.over21" />
			<s:textfield key="person.email"/>
			<s:hidden name="person.id" />
			<s:submit  key="submit" />
		</s:form>
	</body>
</html>
