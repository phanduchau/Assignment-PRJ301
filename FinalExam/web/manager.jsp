<%-- 
    Document   : manager
    Created on : Apr 22, 2024, 12:18:06 PM
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
        <title>Manager Page</title>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
    <div class="container">    
    <h1>Welcome: ${sessionScope.LOGIN_USER.fullName}</h1>
    <c:url var="logoutLink" value="MainController">
        <c:param name="action" value="MainController"></c:param>
    </c:url>
    <a href="${logoutLink}" class="center-text">Logout</a>
    <form action="MainController" method="POST" class="center-text">
        <input type="text" name="searchUser" placeholder="Search User" value="${param.searchUser}"/>
        <input type="submit" name="action" value="searchUser"/></br>
        <input type="text" name="search" placeholder="Search product"value="${param.search}"/>
        <input type="submit" name="action" value="search"/>
    </form>
    <c:if test="${requestScope.LIST_USER != null and not empty requestScope.LIST_USER}">
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>User ID</th>
                    <th>Password</th>
                    <th>Full Name</th>
                    <th>Role ID</th>                   
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" varStatus="counter" items="${requestScope.LIST_USER}">
                <form action="MainController" method="POST">
                    <tr>
                        <td>${counter.count}</td>
                        <td>
                            <input type="text" name="userId" value="${user.userId}" readonly=""/>
                        </td>
                        <td>${user.password}</td>
                        <td>
                            <input type="text" name="fullName" value="${user.fullName}" required=""/>
                        </td>
                        <td>
                            <input type="text" name="role" value="${user.role}" required=""/>
                        </td>                         
                        
                        <td>
                            <input type="hidden" name="searchUser" value="${param.searchUser}" />
                            <input type="submit" name="action" value="UpdateUser"/>
                        </td>
                        <td>
                            <c:url var="deleteLink" value="MainController">
                                <c:param name="userId" value="${user.userId}"></c:param>
                                <c:param name="action" value="DeleteUser"></c:param>
                                <c:param name="searchUser" value="${param.searchUser}"></c:param>
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
</body>
<script>
    <c:if test="${not empty requestScope.ERROR}">
    alert("${requestScope.ERROR}");
    </c:if>
</script>
</div>
</body>
</html>
