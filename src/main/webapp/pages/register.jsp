<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo&display=swap" rel="stylesheet">
    <link rel='stylesheet' href="<c:url value='/resources/theme/css/registerStyle.css'/>">
    <title>Register</title>
</head>
<body>
<form:form cssClass="myForm" modelAttribute="user" action="registerProcess" method="post">
    <h1>Register</h1>
    <label for="firstName">Enter Your First Name:</label><br>
    <input class="inputContainer" path="firstName" id="firstName" type="text" name="firstName"
           placeholder="First Name"
           pattern="([a-zA-Z]+){3,}"
           title="First Name must contain at least 3 characters" required><br>

    <label for="lastName">Enter Your Last Name:</label><br>
    <input class="inputContainer" path="lastName" id="lastName" type="text" name="lastName"
           placeholder="Last Name"
           pattern="([a-zA-Z]+){3,}"
           title="Last name must contain at least 3 characters!"
           required><br>


    <label for="email">Enter Your Email Address:</label><br>
    <input class="inputContainer" path="emailAddress" id="email" type="text" name="emailAddress"
           placeholder="Email Address"
           pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
           required><br>


    <label for="password">Enter Your Password:</label><br>
    <input class="inputContainer" path="password" id="password" type="password" name="password"
           placeholder="Password"
           pattern="(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+){8,}"
           title="Password must contain at least 8 characters
        including both digits and letters!"
           required><br>


    <label for="conPass">Confirm Your Password:</label><br>
    <input class="inputContainer" type="password" id="conPass" name="conPassword"
           placeholder="Confirm Password" required><br>


    <label for="role">Choose Your Role:</label><br>
    <select class="inputContainer" path="role" name="role" id="role" required>
        <option value="" disabled selected hidden>Your Role!</option>
        <option value="Student">Student</option>
        <option value="Teacher">Teacher</option>
    </select><br>

    <button type="submit" name="register">Register</button>
    <br>
</form:form>

<script>
    var password = document.getElementById("password");
    var confirmPassword = document.getElementById("conPass");

    function confirmingPassword() {
        if (password.value != confirmPassword.value) {
            confirmPassword.setCustomValidity("Passwords Don't Match");
        } else {
            confirmPassword.setCustomValidity('');
        }
    }

    password.onchange = confirmingPassword;
    confirmPassword.onkeyup = confirmingPassword;


</script>
</body>
</html>
