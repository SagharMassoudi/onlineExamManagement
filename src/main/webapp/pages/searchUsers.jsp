<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

    <link rel='stylesheet' href="<c:url value='/resources/theme/css/allCoursesStyle.css'/>">
    <title>Search Users</title>
</head>
<body>
<div class="jumbotron text-center jumbotron-fluid" style="margin-bottom: 0; background-color: #579FB9 !important;">
    <h1>Search Users</h1>
</div>
<br><br><br>


<form:form action="searchUsersProcess" method="post" modelAttribute="user">
    <label>First Name:</label>
    <form:input cssClass="inputContainer" type="text" path="firstName" name="firstName"/><br>
    <label>Last Name:</label>
    <form:input cssClass="inputContainer" type="text" path="lastName" name="lastName"/><br>
    <label>Email Address:</label>
    <form:input cssClass="inputContainer" type="text" path="emailAddress" name="emailAddress"/><br>
    <form:select cssClass="inputContainer" path="role" name="role" id="role">
        <option value="" disabled selected hidden>Choose role!</option>
        <option value="Student">Student</option>
        <option value="Teacher">Teacher</option>
    </form:select><br>
    <label>Status:</label>
    <form:input cssClass="inputContainer" type="text" path="status" name="status"/>
<form:button class="btn" type="submit" title="Search">
    <i class="material-icons" style="font-size: 15px">search</i>
</form:button>
</form:form>
<br>
<table align="center">
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email Address</th>
        <th>Role</th>
        <th>Status</th>
    </tr>

    <c:forEach items="${userList}" var="user">
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.emailAddress}</td>
            <td>${user.role}</td>
            <td>${user.status}</td>
        </tr>
    </c:forEach>

</table>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
</body>
</html>
