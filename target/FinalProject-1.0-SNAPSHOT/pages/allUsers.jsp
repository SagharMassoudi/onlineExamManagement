<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

    <link rel='stylesheet' href="<c:url value='/resources/theme/css/allCoursesStyle.css'/>">
<title>All Users</title>
</head>
<body>
<div class="jumbotron text-center jumbotron-fluid" style="margin-bottom: 0; background-color: #579FB9 !important;">
    <h1>All Users</h1>
</div><br><br><br>
<table align="center" width="0">

    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Role</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>


    <c:forEach items="${userList}" var="user">

        <tr>
            <form:form action="updateUser" modelAttribute="user" method="get">

                <form:input id="emailAddress" name="emailAddress" path="emailAddress"
                            value="${user.emailAddress}" cssStyle="display: none"/>

                <td><form:input id="firstName" name="firstName" path="firstName"
                                value="${user.firstName}"/></td>

                <td><form:input id="lastName" name="lastName" path="lastName"
                                value="${user.lastName}"/></td>

                <td>
                    <form:select path="role" name="role" id="role">
                        <option value="${user.role}" selected hidden>
                                ${user.role}</option>
                        <option value="Student">Student</option>
                        <option value="Teacher">Teacher</option>
                        <option value="Admin">Admin</option>
                    </form:select>
                </td>

                <td>
                    <form:select path="status" name="status" id="status">
                        <option value="${user.status}"
                                selected hidden>${user.status}</option>
                        <option value="Confirmed">Confirmed</option>
                    </form:select></td>
                <td>
                    <button type="submit" title="Edit User">
                        <i class="material-icons">edit</i>
                    </button>                </td>
            </form:form>
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
