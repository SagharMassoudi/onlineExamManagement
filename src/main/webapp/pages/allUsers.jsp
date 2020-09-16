<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head></head>
<body>

<table align="center" width="0">

    <tr>
        <th>Id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Role</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>


    <c:forEach items="${userList}" var="user">

        <tr>
            <form:form action="updateUser" modelAttribute="user" method="get">
                <td><form:input id="emailAddress" name="emailAddress" path="emailAddress"
                                value="${user.emailAddress}" readonly="true"/></td>

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
                    <form:button type="submit" value="active">Update User</form:button>
                </td>
            </form:form>
        </tr>
    </c:forEach>
</table>
<script>
</script>

</body>
</html>
