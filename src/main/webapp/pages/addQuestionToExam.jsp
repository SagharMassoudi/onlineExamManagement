<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/css2?family=Fredoka+One&display=swap" rel="stylesheet">
<%--    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"--%>
<%--          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">--%>
    <link rel='stylesheet' href="<c:url value='/resources/theme/css/addQuestionToExamStyle.css'/>">

    <title>Add Question</title>
</head>
<body>
<form:form class="qBank" method="get" action="/getClassificationQuestions">
    <input type="hidden" name="classificationTitle" value="${classificationTitle}">
    <input type="hidden" name="id" id="id" value="${examId}">
    <label for="qBankBtn">Do You Want To Select Question Among Our Question Bank?
        Press The Button Below!</label><br>
    <button type="submit" id="qBankBtn">Question Bank</button>
</form:form><br><br>
<form:form class="newQuestion" method="get" modelAttribute="question" action="/addMultipleChoiceQuestionToExam">

    <input name="classification" id="classification" value="${classificationTitle}" hidden>
    <input name="examId" id="examId" value="${examId}" hidden>

    <label for="questionText">Enter Question Content In The Box Blow:</label><br>
    <form:textarea cssClass="inputContainer" path="questionContent" id="questionText" name="questionText" rows="7"
                   cols="100"
                   required="true"/><br><br>

    <label for="subject">Enter Question Subject In The Box Blow:</label><br>
    <form:input cssClass="inputContainer" path="subject" id="subject" name="subject" required="true"/><br><br>

    <label for="point">Enter Question Point In The Box Below:</label><br>
    <form:input cssClass="inputContainer" path="point" name="point" id="point" required="true"/><br><br>

    <p>Choose Type Of Your Question:</p>

    <label for="essay">Essay Question</label>
    <input type="radio" id="essay" name="questionType"
           onclick="if(this.checked){showEssayRelatedBox()}" required>
    <button id="essayBtn" type="submit" formaction="/addEssayQuestionToExam" hidden>Add Question</button>

    <label for="multipleChoice">Multiple Choice</label>

    <input type="radio" id="multipleChoice" name="questionType"
           onclick="if(this.checked){showMultipleChoiceRelatedBox()}"/>

    <label id="correctAnswerLbl" for="correctAnswer" style="display: none"></label>
    <input class="inputContainer" name="correctAnswer" id="correctAnswer" placeholder="Correct Answer" style="display: none">

    <div id="option">
        <button type="button" id="optionBtn" name="optionBtn" onclick="addNewOption()" hidden>Add False Option</button><br><br>
    </div>

    <p>Do You Want To Add This Question To Our Question Bank?</p>
    <br><br>
    <label for="yes">Yes</label>
    <input type="radio" id="yes" name="questionBank" value="yes" required><br><br>
    <label for="yes">No</label>
    <input type="radio" id="no" name="questionBank" value="no"><br><br>

    <button type="submit" id="multipleChoiceSubmitBtn" hidden>Add Question To Exam</button>
    <button type="submit" id="essaySubmitBtn" formaction="/addEssayQuestionToExam" hidden>Add Question To Exam</button>

</form:form>
<script>
    function showMultipleChoiceRelatedBox() {
        document.getElementById("optionBtn").style.display = "block";
        document.getElementById("multipleChoiceSubmitBtn").style.display = "block";
        document.getElementById("correctAnswerLbl").style.display = "block";
        document.getElementById("correctAnswer").style.display = "block";
    }

    function showEssayRelatedBox() {
        document.getElementById("essaySubmitBtn").style.display = "block";
    }

    function addNewOption() {
        var newFalseOptionInput = document.createElement('div');
        newFalseOptionInput.innerHTML = "<input id='newOption' class='inputContainer' name='falseAnswers' placeholder='False Answer'><br><br>";
        document.getElementById("option").appendChild(newFalseOptionInput);
    }
</script>
</body>
</html>
<%--<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"--%>
<%--        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"--%>
<%--        crossorigin="anonymous"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"--%>
<%--        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"--%>
<%--        crossorigin="anonymous"></script>--%>
<%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"--%>
<%--        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"--%>
<%--        crossorigin="anonymous"></script>--%>


