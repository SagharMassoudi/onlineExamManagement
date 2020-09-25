<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Correcting Page</title>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel='stylesheet' href="<c:url value='/resources/theme/css/allCoursesStyle.css'/>">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            if ($("#lastTime").val() == 1) {
                $("#next").val("Done");
            }
        });
    </script>
</head>
<body>
<div class="jumbotron text-center jumbotron-fluid" style="margin-bottom: 0; background-color: #579FB9 !important;">
    <h1>Correct Essay Question</h1>
</div>
<br><br><br>
<form:form modelAttribute="studentAnswer" method="get" action="correctEssayQuestion">
    <input name="studentEmail" id="studentEmail" value="${studentEmail}" hidden>
    <input name="examId" id="examId" value="${examId}" hidden>
    <input name="questionId" id="questionId" value="${studentAnswer.question.id}" hidden>
    <input name="lastTime" id="lastTime" value="${lastTime}" hidden>
    <h5 style="color: #28515f;text-align: left">${studentAnswer.question.questionContent}</h5>
    <h4 style="color: #28515f ;text-align: left">Answer: </h4><br>
    <p style="color: #28515f; text-align: left">${studentAnswer.answer}</p>
    <label for="point">Point:</label>
    <input class="inputContainer" name="point" id="point" placeholder="Give point to this answer..."><br><br>
    <input class="btn" id="next" type="submit" value="Next">
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
