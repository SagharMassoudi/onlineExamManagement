<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>In Progress Exams</title>
</head>
<body>
<table>
    <tr>
        <th>Exam Subject</th>
        <th>Exam Details</th>
        <th>From (Date)</th>
        <th>To (Date)</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${exams}" var="exam">
        <tr>
            <form:form action="/getExamQuestion" method="get">
                <input type="number" name="examId" id="examId" value="${exam.id}" hidden>
                <input type="number" name="index" id="index" value="0" hidden>
                <td>${exam.subject}</td>
                <td>${exam.moreInfo}</td>
                <td>${exam.startDate}</td>
                <td>${exam.endDate}</td>
                <td>
                    <button type="submit">Start</button>
                </td>
            </form:form>
        </tr>
    </c:forEach>
</table>
</body>
</html>
