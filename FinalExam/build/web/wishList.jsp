<%-- 
    Document   : wishList
    Created on : Apr 22, 2024, 12:19:10 PM
    Author     : Hậu Phan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Wishlist Page</title>
        <link rel="stylesheet" type="text/css" href="userStypes.css">
    </head>
    <body>
        <Script>
            <c:if test ="${not empty requestScope.ERROR}">
            aler("${requestScope.ERROR}");
            </c:if>
        </Script>

        <div class="container">
            <h1>Your Wishlist</h1>
            <c:choose>
                <c:when test="${not empty wishlist}">
                    <c:if test="${fn:length(wishlist) > 0}">
                        <table border="1" class="custom-table">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th>Remove</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="count" value ="1" />
                                <c:forEach var="mobile" items="${wishlist}">
                                <form action ="MainController" method ="POST">
                                    <tr>
                                        <td>${count}</td>
                                        <td>
                                            ${mobile.mobileId}
                                            <input type="hidden" name="mobileId" value="${mobile.mobileId}">
                                        </td>
                                        <td>${mobile.mobileName}</td>
                                        <td>$${mobile.price}</td>
                                        <td>
                                            <c:url var="removeLink" value="MainController" >
                                                <c:param name="mobileId" value="${mobile.mobileId}"></c:param>
                                                <c:param name="action" value="Remove_Wishlist"></c:param>    
                                            </c:url>
                                            <a href="${removeLink}" class="button">Remove</a>
                                        </td>
                                    </tr>
                                </form>
                                <c:set var="count" value="${count + 1}"/>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </c:when>
            </c:choose>
            <br>
            <a href="MainController?action=userSearch" class="button">Add more</a>
        </div>
    </body>
</html>

