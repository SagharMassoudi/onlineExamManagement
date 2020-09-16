<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head></head>
<body>
<form:form cssStyle="margin: 0; width:100" action="searchUsersProcess" method="post" modelAttribute="user">
    <table align="center" width="0">

        <tr>

            <td>
                <form:input cssStyle="border-collapse: white" type="text" placeholder="First Name"
                            path="firstName" name="firstName"/>
            </td>

            <td>
                <form:input type="text" placeholder="Last Name"
                            path="lastName" name="lastName"/>
            </td>

            <td>
                <form:input type="text" placeholder="Email Address"
                            path="emailAddress" name="emailAddress"/>
            </td>

            <td>
                <form:input type="text" placeholder="Status"
                            path="status" name="status"/>
            </td>

            <td>
                <form:select path="role" name="role" id="role">
                    <option value="" disabled selected hidden>Choose role!</option>
                    <option value="Student">Student</option>
                    <option value="Teacher">Teacher</option>
                </form:select>
            </td>

            <td>
                <form:button class="btn" type="submit">Search</form:button>
            </td>

        </tr>

        <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email Address</th>
            <th>Role</th>
            <th>Status</th>
        </tr>

        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.emailAddress}</td>
                <td>${user.role}</td>
                <td>${user.status}</td>
            </tr>
        </c:forEach>

    </table>

</form:form>
</body>
</html>
