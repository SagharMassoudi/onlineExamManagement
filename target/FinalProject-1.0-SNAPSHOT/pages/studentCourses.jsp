<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel='stylesheet' href="<c:url value='/resources/theme/css/allCoursesStyle.css'/>">
    <title>Student Courses</title>
</head>
<body>
<div class="jumbotron text-center jumbotron-fluid" style="margin-bottom: 0; background-color: #579FB9 !important;">
    <h1>Student Courses</h1>
</div>
<br><br><br>
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
                    <button type="submit" title="Finished Exams">
                        <i class="material-icons" style="font-size: 15px">
                            grading
                        </i>
                    </button>
                    <button type="submit" formaction="/showInProgressExams" title="In Progress Exams">
                        <i class="material-icons" style="font-size: 15px">
                            alarm_on
                        </i>
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
