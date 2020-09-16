<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Finished Exams</title>
</head>
<body>
<table>
    <tr>
        <th>Exam Subject</th>
        <th>Exam Details</th>
        <th>Your Score</th>
    </tr>

    <tr>
        <c:forEach items="${finishedExams}" var="studentScore">
            <td>${studentScore.exam.subject}</td>
            <td>${studentScore.exam.moreInfo}</td>
            <td>${studentScore.score}</td>
        </c:forEach>
    </tr>

</table>
</body>
</html>
