<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Spring rest demo</title>
</head>
<body>
<h2>Spring Rest Demo</h2>
<hr>
<a href="${pageContext.request.contextPath}/test/hello">Hello</a>
<hr>
<a href="${pageContext.request.contextPath}/api/students">students</a>
</body>
</html>