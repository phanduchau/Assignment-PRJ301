

<%@page import="user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create User</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/createUser.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <h1>Create new user</h1>
            <form action="MainController" method="POST">
                <input type="text" name="userId" placeholder="Enter UserId" required=""/>${requestScope.USER_ERROR.userIDError}
                <input type="password" name="password" placeholder="Enter Password" required=""/>
                <input type="text" name="fullName" placeholder="Enter Full Name" required=""/>${requestScope.USER_ERROR.fullNameError}
                <input type="text" name="role" value="US" placeholder="Role" readonly=""/>               
                <input type="password" name="confirm" placeholder="Confirm" required=""/>${requestScope.USER_ERROR.confirmError}
                </br><input type="submit" name="action" value="Create"/>
                <input type="reset" value="Reset"/>
                ${requestScope.USER_ERROR.errorError}
            </form>
        </div>
    </body>
</html>
