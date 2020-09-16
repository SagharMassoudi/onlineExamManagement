<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/css2?family=Fredoka+One&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel='stylesheet' href="<c:url value='/resources/theme/css/questionBankStyle.css'/>">
    <title>Question Bank</title>
</head>
<body>
<div class="jumbotron text-center jumbotron-fluid"
     style="margin-bottom: 0; background-color: #221749 !important;">
    <h1>QUESTION BANK</h1>
</div>

<table align="center">
    <tr>
        <th style="width: 30px">Id</th>
        <th>Subject</th>
        <th>Content</th>
        <th>Point</th>
        <th>Action</th>
    </tr>

    <c:forEach items="${questions}" var="question">
        <tr>
            <form:form action="/addQuestionToExamFromQuestionBank" modelAttribute="question" method="get">
                <input type="hidden" name="examId" id="examId" value="${examId}" readonly="true"/>
                <input type="hidden" name="questionId" id="questionId" value="${question.id}" readonly="true"/>
                <td style="width: 30px;text-align: center">${question.id}</td>
                <td>${question.subject}</td>
                <td>${question.questionContent}</td>
                <td><input class="inputContainer" name="point" id="point" placeholder="point" required/>
                </td>
                <td><form:button type="submit">Add To Exam</form:button></td>
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
