<%-- 
    Document   : user
    Created on : Apr 22, 2024, 12:18:27 PM
    Author     : Hậu Phan
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" >
        <title>User Page</title>
        <link href="css/user.css" rel="stylesheet" type="text/css"/>
        
    </head>
    <body>
        <div class="container"> 
            <c:url var="logoutLink" value="MainController">
                <c:param name="action" value="Logout"></c:param>
            </c:url>
            <a href="${logoutLink}" class="center-text">Logout</a>
            <h1>Welcome : ${sessionScope.LOGIN_USER.fullName}</h1>


            <form class ="mainController" action="MainController">
                <div class="button-container">                  

                    <div class="search-container">
                        <input type="number" name="minPrice" value="${param.minPrice}" id="minPrice" min="1" placeholder="Minimum Price"><br>
                        <input type="number" name="maxPrice" value="${param.maxPrice}"id="maxPrice" min="1" placeholder="Maximum Price"><br>
                        <button type="submit" name="action" value="userSearch">Search</button>
                    </div>

                </div>

            </form>



            <c:if test="${requestScope.LIST_PRODUCT != null}">
                <c:if test="${not empty requestScope.LIST_PRODUCT}">
                    <table border="1" class="custom-table">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Mobile ID</th>
                                <th>Description</th>
                                <th>Price</th>
                                <th>Mobile Name</th>
                                <th>Year of Production</th>
                                <th>Image</th>
                                <th>Wishlist</th>
                                <th>Add to Cart</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="product" varStatus="counter" items="${requestScope.LIST_PRODUCT}">
                                <c:if test="${product.notSale !=1}">
                                <form action="MainController" >
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>
                                            ${product.mobileId}
                                            <input type="hidden" name="mobileId" value="${product.mobileId}">
                                        </td>
                                        <td> 
                                            <input type="text" name="description" value="${product.description}" readonly=""/>
                                        </td>
                                        </td>
                                        <td>
                                            <span>$${product.price}</span>
                                            <input type="hidden" name="price" value="${product.price}">
                                        </td>
                                        </td>
                                        <td>
                                            <span>${product.mobileName}</span>
                                            <input type="hidden" name="mobileName" value="${product.mobileName}">
                                        </td>
                                        </td>
                                        <td>
                                            ${product.yearOfProduction}
                                        </td>
                                        <td data-title="image">
                                            <c:set var="image" value="${product.image}" />                                           
                                            <img src="${product.image}" width="120px" height="120px">
                                        </td>


                                        <td>
                                            <button type="submit" name="action" value="Add_Wishlist">Add</button>
                                        </td>
                                        <td>
                                            <button type="submit" name="action" value="Add"/>Add</button>
                                        </td>
                                    </tr>
                                </form>
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </c:if>
            <c:if test="${not empty requestScope.MESSAGE}">
                <p>${requestScope.MESSAGE}</p>
            </c:if>     
            <c:if test="${not empty requestScope.ERROR}">
                <p id="modalMessage">${requestScope.ERROR}</p>
            </c:if>  
            <form action="MainController" method="POST">
                <c:if test="${not empty requestScope.MESSAGE}">
                    <div id="myModal" class="modal">
                        <div class="modal-content">
                            <span class="close" onclick="closeModal()">&times;</span>
                            <h2 id="modalMessage">${requestScope.MESSAGE}</h2>
                            <button type="submit" name="action" value="View"/>View Cart</button>
                        </div>
                    </div>
                </c:if>
                <c:if test="${not empty requestScope.THANKYOU}">
                    <div id="myModal" class="modal">
                        <div class="modal-content">
                            <span class="close" onclick="closeModal()">&times;</span>
                            <h2 id="modalMessage">${requestScope.THANKYOU}</h2>
                            <a href="login.jsp">Back to home page.</a>
                        </div>
                    </div>
                </c:if>   
            </form>
            <script>
                window.onload = function () {
                    showModal();
                };
                function showModal() {
                    var modal = document.getElementById('myModal');
                    modal.style.display = 'block';
                }
                function closeModal() {
                    var modal = document.getElementById('myModal');
                    modal.style.display = 'none';
                }
            </script>    
            <div class="button-container1">
                <form class ="mainController" action="MainController">
                    <button type="submit" name="action" value="Wishlist_Page"/>Wish List</button>
                    <button type="submit" name="action" value="View"/>View</button>
                </form>
            </div>

    </body>
</html>
