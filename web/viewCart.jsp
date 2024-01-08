<%-- 
    Document   : viewCart
    Created on : Oct 13, 2023, 7:23:34 AM
    Author     : tahoa
--%>


<%--<%@page import="java.util.Map"%>
<%@page import="khoatha.cart.CartObject"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <%-- 1.Customer goes to cart place --%>
        <c:if test="${not empty sessionScope}">
            <c:set var="cart" value="${sessionScope.CART}"/>
            <c:if test="${not empty cart}">
                <c:set var="items" value="${cart.items}"/>
                <c:if test="${not empty items}">
                    <form action="cartDispatch">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Name</th>
                                    <th>Quantity</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${items.keySet()}" varStatus="counter">
                                    <tr>
                                        <th>
                                            ${counter.count}
                                        </th>
                                        <th>
                                            ${dto.name}
                                        </th>
                                        <th>
                                            ${items[dto]}
                                        </th>
                                        <th>
                                            <input type="checkbox" name="chkItem" value="${dto.id}" />
                                        </th>
                                    </tr>
                                    
                                </c:forEach>
                                <tr>
                                    <td colspan="3" >
                                        <a href="BookStoreServlet">Add more Books to Your Cart</a>
                                    </td>
                                    <td>
                                        <input type="submit" value="Remove Selected Items" name="btAction"/>
                                    </td>                              
                                </tr>
                            </tbody>
                        </table>
                        <input type="submit" name="btAction" value="Check out"/>
                    </form>
                </c:if> 
            </c:if>

        </c:if>
        <c:if test="${empty sessionScope}">
            <h2>
                No cart is existed!!!
            </h2>
        </c:if>
    </body>
</html>
<%--
<%
//1. Customer goes to cart place
if(session != null) {
    //2. Customer takes his/her cart
    CartObject cart = (CartObject) session.getAttribute("CART");
    if(cart != null) {
        //3. Customer gets items
        Map<String, Integer> items = cart.getItems();
        if(items != null) {
            %>
            <form action="DispatchServlet">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                   <%
                       int count = 0;
                   for(String key: items.keySet()) {
                       %>
                    <tr>
                        <td>
                            <%= ++count  %>
                        </td>
                        <td>
                            <%= key %>
                        </td>
                        <td>
                            <%= items.get(key) %>
                        </td>
                        <td>
                            <input type="checkbox" name="chkItem" value="<%= key %>" />
                        </td>
                    </tr>
                    <%
                   } //end get key value of each items
                   %>
                     <tr>
                         <td colspan="3" >
                             <a href="bookStore.html">Add more Books to Your Cart</a>
                         </td>
                         <td>
                             <input type="submit" value="Remove Selected Items" name="btAction"/>
                         </td>                              
                    </tr>
                </tbody>
            </table>
            </form>
<%
            return;
        } //end items have existed
    } //emd cart has existed
}//cart place must be existed
%>
<h2>
    No cart is existed!!!
</h2>
--%>

