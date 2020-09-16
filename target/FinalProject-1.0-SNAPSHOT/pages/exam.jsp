<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exam</title>
     <script>
        $(document).ready(function () {
            if ($("#index").val() > 1) {
                $("#previous").show();
            }
        });
    </script>
</head>
<body>
<form:form action="/getExamQuestion" modelAttribute="question" method="get">
    <input name="index" id="index" value="${index}" hidden>
    <input name="examId" id="examId" value="${examId}" hidden>
    <input name="questionType" id="questionType"
           value="${question.questionType.name()}" hidden>
    <p>${question.questionContent}</p><br>
    <c:choose>
        <c:when test="${question.questionType.name() == 'MultipleChoiceQuestion'}">
            <c:forEach items="${question.answers}" var="answer">
                <input type="radio" id="${answer.id}" name="choice" value="${answer.content}">
                <label for="${answer.id}">${answer.content}</label><br><br>
            </c:forEach></c:when>

        <c:when test="${question.questionType.name() == 'EssayQuestion'}">
        <textarea id="answerBox" name='studentAnswer' rows='7' cols='100'
                  placeholder='Type Your Answer Here...'></textarea><br><br></c:when>
    </c:choose>

    <input style="display: none" id="previous" type="submit" formaction="/getPreviousQuestion" value="Previous">
    <form:button type="submit">Next</form:button>
</form:form>
</body>
</html>
