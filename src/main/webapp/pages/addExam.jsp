<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel='stylesheet' href="<c:url value='/resources/theme/css/newExamStyle.css'/>">
    <title>Add New Exam</title>
</head>
<body>
<div class="jumbotron text-center jumbotron-fluid"
     style="margin-bottom: 0; background-color: #579FB9 !important;"><br><br>
    <h1>New Exam</h1>
</div>
<form:form modelAttribute="exam" action="/addNewExamToCourseProcess" method="post">

    <input name="examinerEmail" id="examinerEmail" value="${examiner.emailAddress}" hidden>

    <input name="courseTitle" id="courseTitle" value="${course.title}" hidden>

    <label for="subject">Subject:</label>
    <input class="inputContainer" name="subject" id="subject" path="subject" placeholder="Enter The Subject Of Exam" required><br><br>

    <label for="startDate">Start Date:</label>
    <input class="inputContainer" name="startDate" id="startDate" path="startDate" type="date" required><br><br>

    <label for="endDate">End Date:</label>
    <input class="inputContainer" name="endDate" id="endDate" path="endDate" type="date" required><br><br>

    <label for="duration">Duration:</label>
    <input class="inputContainer" name="duration" id="duration" path="duration"
           placeholder="Enter Exam Duration In Minutes" required><br><br>

    <label for="moreInfo">Details:</label>
    <input class="inputContainer" name="moreInfo" id="moreInfo" path="moreInfo"><br><br>
    <form:button type="submit">Done</form:button>
</form:form>
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
