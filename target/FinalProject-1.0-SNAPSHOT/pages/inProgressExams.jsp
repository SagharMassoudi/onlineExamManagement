<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel='stylesheet' href="<c:url value='/resources/theme/css/allCoursesStyle.css'/>">
    <title>In Progress Exams</title>
</head>
<body>
<div class="jumbotron text-center jumbotron-fluid" style="margin-bottom: 0; background-color: #579FB9 !important;">
    <h1>In Progress Exams</h1>
</div><br><br><br>
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
            <form:form action="/startExam" method="get">
                <input type="hidden" id="emailAddress" name="emailAddress" value="${emailAddress}">
                <input type="number" name="examId" id="examId" value="${exam.id}" hidden>
                <input type="number" name="index" id="index" value="0" hidden>
                <td>${exam.subject}</td>
                <td>${exam.moreInfo}</td>
                <td>${exam.startDate}</td>
                <td>${exam.endDate}</td>
                <td>
                    <button type="submit" title="Start Exam">
                        <i class="material-icons" style="font-size: 15px">play_arrow</i>
                    </button>
                </td>
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
