<%--
    Document   : Login
    Created on : Feb 10, 2018, 12:10:31 AM
    Author     : EslamWaheed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
        <link rel="stylesheet" href="css/singnstyle.css">
    </head>
    <body>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel="stylesheet" href="css/normalize.css">
        <link href='https://fonts.googleapis.com/css?family=Nunito:400,300' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>
        <form method="POST" action="Login">
            <h1>Login</h1>
            <fieldset>
                <label for="email">Email:</label>
                <input type="email" name="email" id="email" required>
                <label for="password">Password:</label>
                <input type="password" name="password" id="password" required>
            </fieldset>
            <fieldset>
                <div align="center">
                    <input type="checkbox" name="RemeberMe" value="Remeber Me">
                    <label class="light">Remeber Me</label><br>
                </div>
            </fieldset>
            
            <c:if test = "${sessionScope.loginerror != null}">
                <div align="center">
                    <big style="color: red">Login Error!</big><br><br>
                </div>
                <c:remove var="loginerror" scope="session" />
            </c:if>
            <button type="submit">Login</button>
            
            <a href="SignUp.jsp">signup</a>
            <a href="main.jsp">skip to main</a>
        </form>
        
    </body>
</html>
</body>
</html>
