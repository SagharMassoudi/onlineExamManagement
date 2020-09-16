<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Courses</title>
</head>
<body>
<table align="center">
    <tr>
        <th>Title</th>
        <th>Classification</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${courses}" var="course">
        <tr>
            <form:form action="/showStudentFinishedExams" modelAttribute="course" method="get">
                <input type="hidden" id="emailAddress" name="emailAddress" value="${emailAddress}">

                <td><form:input path="title" name="title" id="title"
                                value="${course.title}" readonly="true"/>
                </td>

                <td><form:input path="classification" name="classification"
                                id="classification" value="${course.classification.title}"
                                readonly="true"/>
                </td>

                <td>
                    <button type="submit">Finished Exams</button>
                    <button type="submit" formaction="/showInProgressExams">In Progress Exams</button>
                </td>
            </form:form>
        </tr>
    </c:forEach>
</table>
</body>
</html>
