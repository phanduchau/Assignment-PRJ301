<%-- 
    Document   : viewCart
    Created on : Apr 22, 2024, 12:18:39 PM
    Author     : Hậu Phan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
        <link href="css/viewCart.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <Script>
            <c:if test ="${not empty requestScope.ERROR}">
            aler("${requestScope.ERROR}");
            </c:if>
            <c:if test="${not empty requestScope.THANKYOU}">
            aler("${requestScope.THANKYOU}");
            </c:if>
        </Script>

        <div class="container">
            <h1>Your Cart</h1>
            <c:choose>
                <c:when test="${not empty sessionScope.CART}">
                    <c:if test="${fn:length(sessionScope.CART.cart) > 0}">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                    <th>Remove</th>
                                    <th>Edit</th
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="count" value ="1" />
                                <c:set var="total" value ="0"/>
                                <c:forEach var="product" items="${sessionScope.CART.cart}">
                                    <c:set var="total" value="${total + (product.value.quantity * product.value.price)}"/>
                                <form action ="MainController" method ="POST">
                                    <tr>
                                        <td>${count}</td>   
                                        
                                        <td>
                                            ${product.value.mobileId}
                                            <input type="hidden" name="mobileId" value="${product.value.mobileId}">
                                        </td>
                                        <td>
                                            ${product.value.mobileName}

                                        </td>
                                        <td>
                                            $${product.value.price}

                                        </td>
                                        <td>
                                            <input min="1" type="number" name="quantity" value ="${product.value.quantity}" required="">
                                        </td>
                                        <td>
                                            $${product.value.quantity * product.value.price}

                                        </td>
                                        <td>
                                            <button type="submit" name="action" value="Remove">Remove</button>
                                        </td>
                                        <td> <button type="submit" name="action" value="Edit">Edit</button>
                                        </td>
                                    </tr>
                                </form>
                                <c:set var="count" value="${count + 1}"/>
                            </c:forEach>
                            </tbody>
                        </table>
                        <h1>Total: $${total}</h1>
                    </c:if>
                </c:when>
            </c:choose>
                    ${requestScope.ERROR}
            <a href="MainController?action=User">Add more</a>
            <a href="MainController?action=Checkout">Check Out</a>
        </div>
    </body>
</html>
