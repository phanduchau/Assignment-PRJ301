<%-- 
    Document   : staff
    Created on : Apr 22, 2024, 12:18:18 PM
    Author     : Hậu Phan
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List" %>
<%@page import="user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Page</title>
        <link href="css/staff.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container"> 
            <c:url var="logoutLink" value="MainController">
                    <c:param name="action" value="MainController"></c:param>
                </c:url>
            <a href="${logoutLink}" class="center-text">Logout</a>
                
            <h1>Welcome: ${sessionScope.LOGIN_USER.fullName}</h1>
            <div class="button-container">
                
                
                <form action="MainController" method="POST" class="center-text">
                    <input type="text" name="search" value="${param.search}"placeholder="Search mobile ID or name"/>
                    <input type="submit" name="action" value="search"/>
                </form>
                <a href="MainController?action=Insert_Mobile_Page">Insert Mobile</a>
            
           
                
            </div>

            <c:if test="${requestScope.LIST_PRODUCT != null and not empty requestScope.LIST_PRODUCT}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>mobileId</th>
                            <th>description</th>
                            <th>price</th>
                            <th>mobileName</th>
                            <th>yearOfProduction</th>
                            <th>image</th>
                            <th>quantity</th>                           
                            <th>notsale</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" varStatus="counter" items="${requestScope.LIST_PRODUCT}">
                        <form action="MainController" method="POST">
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    <input type="text" name="mobileId" value="${product.mobileId}" readonly=""/>
                                </td>
                                <td>
                                    <input type="text" name="description" value="${product.description}" required=""/>
                                </td>
                                <td>
                                    <input type="text" name="price" value="${product.price}" required=""/>
                                </td>   
                                <td>
                                    <input type="text" name="mobileName" value="${product.mobileName}" required=""/>
                                </td> 
                                <td>
                                    <input type="text" name="yearOfProduction" value="${product.yearOfProduction}" required=""/>
                                </td> 
                                <td data-title="image">
                                            <c:set var="image" value="${product.image}" />                                           
                                            <img src="${product.image}" width="120px" height="120px">
                                </td>
                                <td>
                                    <input type="number" name="quantity" value="${product.quantity}" required=""/>
                                </td>  
                                <td>
                                    <input type="number" name="notSale" value="${product.notSale}" min="0" max="1" required=""/>
                                </td>  
                                <td>
                                    <input type="hidden" name="search" value="${param.search}" />
                                    <input type="submit" name="action" value="Update"/>
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="MainController">
                                        <c:param name="mobileId" value="${product.mobileId}"></c:param>
                                        <c:param name="action" value="Delete"></c:param>
                                        <c:param name="search" value="${param.search}"></c:param>
                                    </c:url>
                                    <a href="${deleteLink}" class="center-text">Delete</a>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>    
            <script>
                <c:if test="${not empty requestScope.ERROR}">
        alert("${requestScope.ERROR}");
                </c:if>
            </script>
        </div>
    </body>
</html>
