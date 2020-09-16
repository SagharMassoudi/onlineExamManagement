<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/css2?family=Fredoka+One&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel='stylesheet' href="<c:url value='/resources/theme/css/newCourseStyle.css'/>">
    <title>Add New Course</title>
</head>
<body>
<div class="jumbotron text-center jumbotron-fluid" style="margin-bottom: 0; background-color: #221749 !important;">
    <h1>NEW<span> COURSE</span></h1>
</div>
<form:form modelAttribute="course" action="addNewCourseProcess" method="post">
    <label for="title">Enter The Title Of Course:</label>
    <input class="inputContainer" path="title" id="title" type="text" name="title"
           placeholder="Title" required>l

    <label for="classificationTitle">Choose Classification</label><br>
    <select class="inputContainer" id="classificationTitle" name="classificationTitle" required>
        <option value="" disabled selected hidden>Classification</option>
        <c:forEach items="${classifications}" var="classification">
            <option value="${classification.title}">${classification.title}</option>
        </c:forEach>
    </select>
    <button type="submit">Save</button>

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
