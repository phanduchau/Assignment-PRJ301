<%-- 
    Document   : insert
    Created on : Apr 22, 2024, 12:17:54 PM
    Author     : Hậu Phan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Page</title>
        <link href="css/insert.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <h1>Insert new Mobile</h1>
            <form action="MainController" method="POST">
               <input type="text" name="mobileId" placeholder="Mobile ID"required=""/>
                <input type="text" name="description" placeholder="Description"required=""/>
                <input type="text" name="price" placeholder="Price"required=""/>
                <input type="text" name="mobileName" placeholder="Mobile Name"required=""/>
                <input type="number" name="yearOfProduction" placeholder="Year of Production"required=""/>
                <input type="text" name="image" placeholder="image url" required=""/>
                <input type="number" name="quantity" placeholder="Quantity" required=""/>
                <input type="number" name="notSale" placeholder="NotSale" max="1" min="0"required=""/>
                <c:if test="${not empty requestScope.PRODUCT_ERROR.mobileIdError || not empty requestScope.PRODUCT_ERROR.mobileNameError }">
                    <c:choose>
                        <c:when test="${not empty requestScope.PRODUCT_ERROR.mobileIdError}">   
                            ${requestScope.PRODUCT_ERROR.mobileIdError}
                        </c:when>
                        <c:when test="${not empty requestScope.PRODUCT_ERROR.mobileNameError}">   
                            ${requestScope.PRODUCT_ERROR.mobileNameError}
                        </c:when>
                        <c:when test="${not empty requestScope.PRODUCT_ERROR.errorError}">   
                            ${requestScope.PRODUCT_ERROR.errorError}
                        </c:when>
                    </c:choose>
                </c:if>
                </br><input type="submit" name="action" value="Insert"/>
                <input type="reset" value="Reset"/>

                ${requestScope.USER_ERROR.errorError} 
            </form>
            <a href="staff.jsp">View</a>
        </div>
    </body>
</html>
