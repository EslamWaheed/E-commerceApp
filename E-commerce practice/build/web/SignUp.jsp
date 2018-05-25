<%--
    Document   : SignUp
    Created on : Feb 6, 2018, 9:19:54 PM
    Author     : EslamWaheed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Sign Up</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
        <link rel="stylesheet" href="css/singnstyle.css">
    </head>
    <body>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>SignUp</title>
        <link rel="stylesheet" href="css/normalize.css">
        <link href='https://fonts.googleapis.com/css?family=Nunito:400,300' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/main.css">
    <body>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>SignUp</title>
        <link rel="stylesheet" href="css/normalize.css">
        <link href='https://fonts.googleapis.com/css?family=Nunito:400,300' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>
        <form method="POST" action="SignUp" enctype="multipart/form-data">
            <h1>SignUp</h1>
            <fieldset>
                <legend><span class="number">1</span>Your basic info</legend>
                <label for="name">Name:</label>
                <input type="text" name="name" id="name" required>
                <label for="email">Email:</label>
                <input type="email" name="email" id="email" required>
                <label for="password">Password:</label>
                <input type="password" name="password" id="password" required>
                <label for="db">DateOfBirth:</label>
                <input type="date" name="db" id="db" required>
                <label for="job">Job:</label>
                <input type="text" name="job" id="job" required> 
                <label for="address">Address:</label>
                <input type="text" name="address" id="address" required>
                <label for="credit_limit">credit_limit:</label>
                <input type="number" name="credit_limit" id="credit_limit" required>
            </fieldset>
            <fieldset>
                <label>Interests:</label>
                <input type="checkbox" name="clothes" value="clothes">
                <label class="light">Clothes</label><br>
                <input type="checkbox" name="food" value="food">
                <label class="light">Food</label><br>
                <input type="checkbox" name="electronic" value="electronic">
                <label class="light">Electronics</label><br>
            </fieldset>
            <input type="file" name="myimg" accept="image/x-png,image/gif,image/jpeg">
            <c:if test = "${sessionScope.error != null}">
                <div align="center">
                    <big style="color: red">User Already Exists!</big><br><br>
                </div>
                <c:remove var="error" scope="session" />
            </c:if>
            <button type="submit">SignUp</button>
            <a href="Login.jsp">login</a>
            <a href="main.jsp">skip to main</a>
        </form>
        
    </body>
</html>
</body>
</html>
