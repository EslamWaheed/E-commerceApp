<%-- 
    Document   : profileUser
    Created on : Feb 9, 2018, 1:16:13 PM
    Author     : Aya
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

   <%@page import="util.User"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Bx slider css files -->
        <link rel="stylesheet" href="css/jquery.bxslider.min.css"/>
        <!-- css files -->
        <link rel="stylesheet" href="css/normalize.css"/>
        <link rel="stylesheet" href="css/web.css"/>
        <!-- Font awesome iconic librery -->
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        
        <!--===============================================================================================-->
	<link rel="icon" type="image/png" href="images/icons/favicon.png"/>
        <!--===============================================================================================-->
                <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
                <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
        <!--===============================================================================================-->
                <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
                <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
        <!--===============================================================================================-->
                <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
        <!--===============================================================================================-->
                <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
        <!--===============================================================================================-->
                <link rel="stylesheet" type="text/css" href="vendor/slick/slick.css">
        <!--===============================================================================================-->
                <link rel="stylesheet" type="text/css" href="vendor/lightbox2/css/lightbox.min.css">
        <!--===============================================================================================-->
                <link rel="stylesheet" type="text/css" href="css/util.css">
                <link rel="stylesheet" type="text/css" href="css/main.css">
                <link rel="stylesheet" type="text/css" href="css/main-header.css">
        <!--===============================================================================================-->
        
    </head>
    <body>
        
        
        
        
        <!-- Header -->
	<header class="header1">
		<!-- Header desktop -->
		<div class="container-menu-header">
			

			<div class="wrap_header">
				<!-- Logo -->
				<a href="main.jsp" class="logo">
					<img src="images/icons/logo.png" alt="IMG-LOGO">
				</a>

				<!-- Menu -->
				

				<!-- Header Icon -->
				<div class="header-icons">

                                            <c:if test = "${sessionScope.user != null}">
                                                welcom:<small style="color: green"> ${sessionScope.user.getName()}</small>
                                                <img style="width: 50px;height: 50px;border-radius: 25px" src="images/users/${sessionScope.user.getId()}.png">
                                                <form action="ProfUser" method="get">
                                                    <input type="hidden" name="prof" value="load"/>
                                                    <input type="hidden" name="userId" value="1"/>
                                                    <input type="submit" value="showProfile"/>
                                                </form>
                                                <form method="post" action="Logout">
                                                    <input type="submit" value="Logout">
                                                </form>
                                            </c:if>
                                                
                                            <c:if test = "${sessionScope.user == null}">
                                                welcom:<small style="color: green"> </small>
                                                <a href="Login.jsp">login</a>
                                                <a href="SignUp.jsp">signup</a>
                                                
                                            </c:if>    
	
				</div>
			</div>
		</div>
        </header>
        <!--/header-->
        <div class="cnt">
            <h2 style="margin-left: 100px">Person Profile</h2>
        <img src="/E-commercepractice/images/users/${sessionScope.user.getId()}.png" class="imgu" style="width: 50px;height: 50px;border-radius: 25px">
        <form action="ProfUser" method="post" class="well">
            <input type="hidden" name="prof" value="update"/>
            <input hidden="hidden" name="userId" value="${TheUser.id}"/>
            <legend> Name </legend>
                <input type="text" class="prodDet1" name="userName" value="${TheUser.name}"/>
             <legend> Birth date </legend>
            <input type="text" class="prodDet1" name="Birthd" value="${TheUser.bd}"/>
              <legend>  Password </legend>
                <input type="text" class="prodDet1" name="password" value="${TheUser.password}"/>
               <legend> Job</legend>
               <input type="text"  class="prodDet1" name="job" value="${TheUser.job}"/>
               <legend> Email </legend>
                <input type="text" class="prodDet1" name="email" value="${TheUser.email}"/>
              <legend>  Address </legend>
                <input type="text" class="prodDet1" name="address" value="${TheUser.address}">
                <legend>Credit limit </legend>
                <input type="text" class="prodDet1" name="credit" value="${TheUser.credit_limit}"/>
            <input type="submit" value="submit" id="AddProd"/>
        </form>
            <form method="post" action="userHistory">
                <input hidden="hidden" name="userId" value="${TheUser.id}"/>
                <input type="submit" value="my order history"/>
            </form>
                
        </div>
    </body>
</html>
