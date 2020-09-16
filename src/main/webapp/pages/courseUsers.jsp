<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head></head>
<body>
<table>
    <tr>
        <th>Id</th>
        <th>First Name</th>
        <th>Last Name</th>

    </tr>
    <c:forEach items="${users}" var="user">
    <td>${user.id}</td>
    <td>${user.firstName}</td>
    <td>${user.lastName}</td>
    <tr>
        </c:forEach>
    </tr>
</table>
</body>
</html>
