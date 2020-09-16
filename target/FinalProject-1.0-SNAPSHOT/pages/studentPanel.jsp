<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Panel</title>
</head>
<body>
<form:form method="get" action="/showUserCourses">
    <h3>${message}</h3>
    <input type="hidden" id="emailAddress" name="emailAddress" value="${emailAddress}">
    <button type="submit">My Courses</button>
</form:form>
</body>
</html>
