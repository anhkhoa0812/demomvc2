<%-- 
    Document   : createAccount
    Created on : Oct 13, 2023, 9:14:15 AM
    Author     : tahoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <h1>Create Account</h1>
        <form action="createAccountController" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERRORS}"/>
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}"/>(6-30 chars) <br>
            <c:if test="${not empty errors.usernameLengthErr}">
                <font color="red">
                    ${errors.usernameLengthErr}
                </font>
            </c:if><br>
             <c:if test="${not empty errors.usernameisExisted}">
                <font color="red">
                    ${errors.usernameisExisted}
                </font>
            </c:if><br>
            Password* <input type="password" name="txtPassword" value=""/>(6-20 chars) <br>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color="red">
                    ${errors.passwordLengthErr}
                </font>
            </c:if><br>
            Confirm* <input type="password" name="txtConfirm" value=""/><br>
                <c:if test="${not empty errors.confirmNotMached}">
                <font color="red">
                    ${errors.confirmNotMached}
                </font>
            </c:if><br>
            Full name* <input type="text" name="txtFullName" value="${param.txtFullName}"/>(2-50 chars)<br>
            <c:if test="${not empty errors.fullNameLengthErr}">
                <font color="red">
                    ${errors.fullNameLengthErr}
                </font>
            </c:if><br>
            <input type="submit" value="Create New Account" name="btAction"/>
            <input type="reset" value="Reset"/>
        </form>
    </body>
</html>
