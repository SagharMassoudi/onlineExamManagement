<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head></head>
<body>
<table align="center">

    <tr>
        <th>Id</th>
        <th>Course Title</th>
        <th>Classification</th>
        <th>Actions</th>
    </tr>


    <tr>
        <c:forEach items="${courseList}" var="course">
        <form:form modelAttribute="course" action="showCourseUsers" method="get">

            <td><form:input path="id" name="id" id="id" value="${course.id}"
                            readonly="true"/>
            </td>

            <td><form:input path="title" name="title" id="title"
                            value="${course.title}" readonly="true"/>
            </td>

            <td><form:input path="classification" name="classification"
                            id="classification" value="${course.classification.title}"
                            readonly="true"/>
            </td>

            <td>
                <button type="submit">See Users</button>
            </td>
        </form:form>
    </tr>
    </c:forEach>

</table>
</body>
</html>
