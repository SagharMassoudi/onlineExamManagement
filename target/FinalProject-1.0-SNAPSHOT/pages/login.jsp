<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/css2?family=Fredoka+One&display=swap" rel="stylesheet">
    <link rel='stylesheet' href="<c:url value='/resources/theme/css/loginStyle.css'/>">
    <title>Login Page</title>
</head>
<body>
<div class="container"><form:form cssClass="myForm" modelAttribute="user" action="loginProcess" method="get">
    <h2>Login</h2><br>
    <label for="emailAddress">Enter Your Email Address:</label><br>

    <input class="inputContainer" path="emailAddress" type="text" name="emailAddress"
           id="emailAddress"
           placeholder="Email Address" required><br>


    <label for="password">Enter Your Password:</label><br>

    <input class="inputContainer" path="password" type="password" name="password"
           id="password"
           placeholder="Password" required><br>


    <label for="role">Choose Your Role:</label><br>
    <select class="inputContainer" path="role" name="role" id="role" required>
        <option value="" disabled selected hidden>Your Role!</option>
        <option value="Admin">Admin</option>
        <option value="Student">Student</option>
        <option value="Teacher">Teacher</option>
    </select><br>


    <button type="submit" name="login">Login</button>
    <br>

</form:form></div>


</body>
</html>
