<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exam</title>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel='stylesheet' href="<c:url value='/resources/theme/css/allCoursesStyle.css'/>">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            if ($("#index").val() > 1) {
                $("#previous").show();
            }
            if ($("#index").val() == $("#listSize").val()) {
                $("#next").hide();
                $("#done").show();
            }

            function myTimer() {
                const startTime = Date.parse($("#startTime").val());
                var duration = Number.parseInt($("#examDuration").val());
                var durationSec = duration * 60;
                var now = Date.now();
                var passedTime = Math.abs(now - startTime);
                var passedTimeSec = passedTime / 1000;
                var remainingTime = durationSec - passedTimeSec;
                var h = Math.floor(remainingTime / 3600);
                var m = Math.floor(remainingTime % 3600 / 60);
                var s = Math.floor(remainingTime % 3600 % 60);
                var hDisplay = h > 0 ? h + (h == 1 ? " hour " : " hours: ") : "";
                var mDisplay = m > 0 ? m + (m == 1 ? " minute " : " minutes: ") : "";
                var sDisplay = s > 0 ? s + (s == 1 ? " second" : " seconds") : "";
                $("#remainingTime").html(hDisplay + mDisplay + sDisplay);
                if (remainingTime == 0) {
                    $("#done").click();
                }
            }

            setInterval(function () {
                myTimer()
            }, 1000)
        });
    </script>
</head>
<body>
<div class="jumbotron text-center jumbotron-fluid" style="margin-bottom: 0; background-color: #579FB9 !important;">
    <h1 id="remainingTime"></h1>
</div>
<br><br><br>
<form:form cssStyle="height: 500; width: 700" action="/getExamQuestion" modelAttribute="question" method="get">
    <input name="examDuration" id="examDuration" value="${examDuration}" hidden>
    <input name="startTime" id="startTime" value="${startTime}" hidden>
    <input type="hidden" name="emailAddress" id="emailAddress" value="${emailAddress}">
    <input name="listSize" id="listSize" value="${listSize}" hidden>
    <input name="index" id="index" value="${index}" hidden>
    <input name="examId" id="examId" value="${examId}" hidden>
    <input name="questionType" id="questionType"
           value="${question.questionType.name()}" hidden>
    <h4 style="color: #579FB9">${question.questionContent}</h4><br>
    <c:choose>

        <c:when test="${question.questionType.name() == 'MultipleChoiceQuestion'}">
            <c:forEach items="${question.answers}" var="answer">
                <input type="radio" id="${answer.id}" name="choice" value="${answer.content}">
                <label for="${answer.id}">${answer.content}</label><br><br>
            </c:forEach>
        </c:when>

        <c:when test="${question.questionType.name() == 'EssayQuestion'}">
        <textarea id="answerBox" name='studentAnswer' rows='7' cols='70'
                  placeholder='Type Your Answer Here...'></textarea><br><br></c:when>
    </c:choose>

    <input class="btn" style="display: none" id="previous" type="submit" formaction="/getPreviousQuestion"
           value="Previous">
    <input class="btn" id="next" value="Next" type="submit"/>
    <input class="btn" id="done" value="Done" type="submit" formaction="/finishExam" hidden/>
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
