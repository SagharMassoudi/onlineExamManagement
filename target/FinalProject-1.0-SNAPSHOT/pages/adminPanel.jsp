<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel='stylesheet' href="<c:url value='/resources/theme/css/adminPanelStyle.css'/>">
    <title>Admin Panel</title>
</head>
<body>
<div class="jumbotron text-center jumbotron-fluid" style="margin-bottom: 0; background-color: #579FB9 !important;">
    <h1>Admin Panel</h1>
</div><br><br><br>
<form>
    <h3>${message}</h3>
    <input type="hidden" name="emailAddress" id="emailAddress" value="${emailAddress}">
    <label>Click On Button Below, In Order To Manage Online Exam System</label>
    <button type="button" onclick="document.location='/searchUsers'">Search Through Users</button>
    <br><br>
    <button type="button" onclick="document.location='/showAllCourses'">Courses</button>
    <br><br>
    <button type="button" onclick="document.location='/showAllUsers'">Users</button>
    <br><br>
    <button type="button" onclick="document.location='/addCourse'">New Course</button>
    <br><br>
    <button type="button" onclick="document.location='/addClassification'">New Classification</button>
    <br><br>
    <button type="button" onclick="document.location='/addUserToCourse'">Add New User To Course</button>
    <br><br>
</form>
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
