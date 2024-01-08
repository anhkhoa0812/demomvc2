<%-- 
    Document   : search
    Created on : Sep 29, 2023, 8:57:47 AM
    Author     : tahoa
--%>

<%--<%@page import="khoatha.registration.RegistrationDTO"%>
<%@page import="java.util.List--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body> 
        <font color="red">
        Welcome, ${sessionScope.USER_INFO.fullName}
        </font>
        <form action = "logoutController" method= "GET">
            <input type ="SUBMIT" name ="btAction" value ="Logout"/>
        </form>
        <h1>Search Page</h1>
        <form action="searchLastNameController" method="POST">
            Search Value <input type="text" name="txtSearch"  value="${param.txtSearch}" /> <br>
            <input type="submit" name="btAction" value="Search" /> 
        </form><br>
        <c:set var="searchValue" value="${param.txtSearch}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">

                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full Name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${result}" var="dto" varStatus="counter">
                        <form action="updateAccountController" method="POST">       
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.fullName}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="urlRewriting" value="deleteAccountController">
                                        <c:param name="btAction" value="delete"></c:param>
                                        <c:param name="pk" value="${dto.username}"></c:param>
                                        <c:param name="lastSearchValue" value="${searchValue}"></c:param>
                                    </c:url>
                                    <a href="${urlRewriting}">Delete</a>
                                </td>
                                <td>
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                    <input type="submit" value="Update" name="btAction"/>
                                </td>
                            </tr>

                        </form>       
                    </c:forEach>
                </tbody>
            </table>
            <c:set var="error" value="${requestScope.PASSWORD_ERROR}"/> 
            <c:if test="${not empty error}">
                <font color="red">${error}</font>
            </c:if>
            <c:set var="noti" value="${requestScope.SUCCESS_NOTI}"/> 
            <c:if test="${not empty noti}">
                <font color="red">${noti}</font>
            </c:if>
        </c:if>
        <c:if test="${empty result}">
            <h2>
                No record is matched!!!
            </h2>

        </c:if>
    </c:if>
</body>
</html>
<%--<%
    Cookie[] cookies = request.getCookies();
    if (cookies != null) { //Check cookies ton tai hay ko
        Cookie newestCookie = cookies[cookies.length - 1];
        String username = newestCookie.getName();
%>
<font color="red">Welcome, <%= username%></font>
<%
    } //end cookies have existed
%>
<form action = "DispatchServlet" method= "GET">
    <input type ="SUBMIT" name ="btAction" value ="Logout"/>
</form>
<h1>Search Page</h1>
<form action="DispatchServlet" method="POST">
    Search Value <input type="text" name="txtSearch"  value="<%= request.getParameter("txtSearch")%>" /> <br>
    <input type="submit" name="btAction" value="Search" /> 
</form><br>
<%
    String searchValue = request.getParameter("txtSearch");
    if (searchValue != null) {
        List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT"); // No lay tu attribute va phai ep kieu tuong minh
        //render
        if (result != null) {// has one or more records
%>
<table border="1">
    <thead>
        <tr>
            <th>No.</th>
            <th>Username</th>
            <th>Password</th>
            <th>Full name</th>
            <th>Role</th>
            <th>Delete</th>
            <th>Update</th>
        </tr>
    </thead>
    <tbody>
        <%
            int count = 0;
            for (RegistrationDTO dto : result) {
                String urlRewriting = "DispatchServlet"
                        + "?btAction=delete"
                        + "&pk=" + dto.getUsername()
                        + "&lastSearchValue=" + request.getParameter("txtSearch");
        %>
    <form action="DispatchServlet" method="POST">
        <tr>
            <td>
                <%= ++count%>
            </td>
            <td>
                <%= dto.getUsername()%>
                <input type="hidden" name="txtUsername" value="<%= dto.getUsername()%>" />
            </td>
            <td>
                <input type="text" name="txtPassword" value="<%= dto.getPassword()%>" />
            </td>
            <td>
                <%= dto.getFullName()%>
            </td>
            <td>
                <input type="checkbox" name="chkAdmin" value="ON"  
                       <%
                           if (dto.isRole()) {
                       %>
                       checked="checked"
                       <%
                           } //user is an admin
                       %>
                       />
            </td>
            <td>
                <a href="<%= urlRewriting%>">Delete</a>
            </td>
            <td>
                <input type="hidden" name="lastSearchValue"
                       value="<%= request.getParameter("txtSearch")%>"/>
                <input type="submit" value="Update" name="btAction"/>
            </td>
        </tr>
    </form>
    <%
        } //end traverse DAO
    %>
</tbody>
</table>

    <%
    } else { // no record
    %>
    <h2>
        No record is matched !!!
    </h2>
    <%
            }
        } // end searchValue dit not trigger from previous form
    %>
--%>

