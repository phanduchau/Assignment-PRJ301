<%-- 
    Document   : login
    Created on : Apr 22, 2024, 12:17:11 PM
    Author     : Hậu Phan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <h1>Login</h1>
            <form id="form" action="MainController" method="POST">
                <input id="userId" type="text" name="userId" placeholder="Enter UserId"required/></br>
                <input id="password" type="password" name="password" placeholder="Enter Password"required/></br>
                <input type="submit" name="action" value="Login"/>
                <input type="reset" value="Reset"/> </br>                              
            <div class ="register">
               <a href="MainController?action=Create_User_Page">Register</a> 
            </div>    
            </form>  
        </div>
    </body>
</html>
