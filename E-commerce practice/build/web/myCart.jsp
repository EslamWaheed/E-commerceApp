<%-- 
    Document   : myCart
    Created on : Feb 9, 2018, 11:24:16 AM
    Author     : Hazem
--%>

<%@page import="java.util.*"%>
<%@page import="util.MyCart" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style-cart.css" media="screen" title="no title" charset="utf-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        
            
            <div class="shopping-cart">
            <!-- Title -->
            <div class="title">
                My Cart
            </div>
            <c:forEach var="product" items="${sessionScope.mycart}">
                <!-- Product #1 -->
                <div class="item">
                    
                    <form method="git" action="deleteFromCart">
                        <input type="hidden" name="deleteditem" value="${product.getProductId()}">
                               
                        <button>delete</button>
                    </form>    
                    

                    <div class="image">
                        <img src="item-1.png" alt="" />
                    </div>

                <div class="description">
                    <span>${product.getCategoryName()}</span>
                    <span>${product.getProductName()}</span>
                </div>

                <div class="quantity">
                    <form method="post" action="ProductPlus" style="display:inline">
                        <button class="plus-btn" type="submit" name="button">
                            <input type="hidden" name="deleteditem" value="${product.getProductId()}">
                            <img src="images/plus.svg" alt="" />
                        </button>
                    </form>
                    
                    <input type="text" name="name" value="${product.getProductQuantity()}" disabled="true">
                    <form method="post" action="ProductMinus" style="display:inline">
                        <button class="minus-btn" type="submit" name="button">
                            <input type="hidden" name="deleteditem" value="${product.getProductId()}">
                            <img src="images/minus.svg" alt="" />
                        </button>
                    </form>
                     <c:if test = "${sessionScope.max != null}">
                                <small style="color: red">max quantity ....</small>
                                <c:remove var="max" scope="session" />

                            </c:if>
                            <c:if test = "${sessionScope.min != null}">
                                <small style="color: red">min quantity ....</small>
                                <c:remove var="min" scope="session" />

                            </c:if>
                    
                </div>

                <div class="total-price">${product.getProductPrice()} EP</div>
                </div>
        
            </c:forEach>
                <h3 style="display: inline">total price is: </h3> <span>${sessionScope.countprices}</span>
                <form method="post" action="checkout">
                    <button type="submit">check out</button>
                </form>
                <c:if test = "${sessionScope.nocredit != null}">
                    <small style="color: red">your credit not enough ....</small>
                    <c:remove var="nocredit" scope="session" />
                </c:if>
                <c:if test = "${sessionScope.broductNotExist != null}">
                    <small style="color: red">${sessionScope.broductNotExist} not still exist ....</small>
                    <c:remove var="broductNotExist" scope="session" />
                </c:if>
                
            </div>
        
        
              
        
        
        
    </body>
</html>
