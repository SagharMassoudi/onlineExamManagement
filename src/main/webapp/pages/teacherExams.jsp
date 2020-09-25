<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel='stylesheet' href="<c:url value='/resources/theme/css/courseExamsStyle.css'/>">
    <title>Course Exams</title>
</head>
<body>
<div class="jumbotron text-center jumbotron-fluid"
     style="margin-bottom: 0; background-color: #579FB9 !important;">
    <h1 id="header">Exams</h1>
</div>
<br><br><br>

<table align="center">
    <tr>
        <th>Subject</th>
        <th>From</th>
        <th>To</th>
        <th>Duration</th>
        <th>Examiner</th>
        <th>Status</th>
        <th>Total score</th>
        <th>More Information</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${exams}" var="exam">
    <tr>
        <form:form action="/editExam" modelAttribute="exam" method="get">

            <input type="hidden" id="emailAddress" name="emailAddress"
                   value="${emailAddress}">

            <input name="id" id="id" value="${exam.id}" hidden/>

            <td>
                <form:input class="inputContainer" path="subject" name="subject" id="subject"
                            value="${exam.subject}"/>
            </td>

            <td>
                <form:input class="inputContainer" cssStyle="width: 120px" path="startDate" name="startDate"
                            id="startDate"
                            value="${exam.startDate}"/>
            </td>

            <td>
                <form:input class="inputContainer" cssStyle="width: 120px" path="endDate" name="endDate" id="endDate"
                            value="${exam.endDate}"/>
            </td>

            <td>
                <input class="inputContainer" style="width: 70px;" name="duration" id="duration"
                       value="${exam.duration}"/>
            </td>

            <td><form:input class="inputContainer" path="examiner" name="examiner" id="examiner"
                            value="${exam.examiner.firstName} ${exam.examiner.lastName}"
                            readonly="true"/></td>

            <td>
                <form:input class="inputContainer" cssStyle="width: 100px" path="status" name="status" id="status"
                            value="${exam.status.name()}" readonly="true"/>
            </td>

            <td><form:input class="inputContainer" path="totalScore" cssStyle="width: 70px" name="totalScore"
                            id="totalScore"
                            value="${exam.totalScore}" readonly="true"/>
            </td>

            <td><form:input class="inputContainer" path="moreInfo" name="moreInfo"
                            id="moreInfo" value="${exam.moreInfo}"/>
            </td>

            <td>
                <button type="submit" title="Edit Exam">
                    <i class="material-icons" style="font-size: 15px">edit</i>
                </button>
                <button type="submit" title="Add New Question To This Exam"
                        formaction="/addQuestionToExam">
                    <i class="material-icons" style="font-size: 15px">add</i>
                </button>
                <button type="submit" title="Cancel This Exam"
                        formaction="/cancelExam">
                    <i class="material-icons " style="font-size: 15px">cancel</i>
                </button>
                <button type="submit" title="Delete This Exam"
                        formaction="/deleteExam">
                    <i class="material-icons" style="font-size: 15px">delete</i>
                </button>
                <button type="submit" title="Results Of This Exam"
                        formaction="/showExamResults">
                    <i class="material-icons" style="font-size: 15px">playlist_add_check</i>
                </button>
            </td>
        </form:form>
    </tr>
    </c:forEach>
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
