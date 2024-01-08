<%-- 
    Document   : bookStore
    Created on : Oct 12, 2023, 5:28:00 PM
    Author     : tahoa
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="khoatha.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Book Store</h1>

        <c:set var="result" value="${requestScope.BOOK_LIST}"/>
        <c:if test="${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}" varStatus="counter">
                    <form action="addToCartController">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${dto.name}
                            </td>
                            <td>
                                ${dto.unitPrice}
                            </td>
                            <td>
                                <%--${dto.quantity} --%>
                                <input type="text" name="bookQuantity" value=""/>
                            </td>
                            <td>
                                <c:if test="${dto.status == true}">
                                    <input type="submit" value="Add to Cart" name="btAction"/>
                                    <input type="hidden" name="bookID" value="${dto.id}"/>
                                </c:if>
                                <c:if test="${dto.status == false}">
                                    <font>Sold out</font>
                                </c:if>
                            </td>
                        </tr>
                    </form>
                </c:forEach>

            </tbody>
        </table>
        <c:set var="formatNumberError" value="${requestScope.ERROR_NUMBERFORMAT}"/>
        <c:if test="${not empty formatNumberError}">
            <font color="red">${formatNumberError}</font>
        </c:if>
        <c:set var="quantityError" value="${requestScope.ERROR_QUANTITY}"/>
        <c:if test="${not empty quantityError}">
            <font color="red">${quantityError}</font>
        </c:if>
        <form action="viewCartPage">
            <input type="submit" value="View Your Cart" name="btAction"/>
        </form>
    </c:if>

</body>
</html> 
<%--<h1>Book Store</h1>
<form action="DispatchServlet">
    <%
    List<ProductDTO> result = (List<ProductDTO>) request.getAttribute("BOOK_LIST");
    if(result != null) {
    %>
    <table border="1">
        <thead>
            <tr>
                <th>No.</th>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%
            int count  =0;
            for(ProductDTO dto: result) {
            %>
            <tr>
                <td>
                    <%= ++count %>
                </td>
                <td>
                    <%= dto.getName() %>
                </td>
                <td>
                    <%= dto.getUnitPrice() %>
                </td>
                <td>
                    <input type="text" name="quantity" value=""/> 
                </td>
                <td>
                    <input type="submit" value="Add to Cart" name="btAction"/>
                    <input type="hidden" name="bookName" value="<%= dto.getId() %>"/>
                </td>
            </tr>
            <%
            }
            %>
        </tbody>
    </table>
    <%
    } else {
%>
<p>Please try again!!</p>
    <%
}
    %>
    <input type="submit" value="View Your Cart" name="btAction"/>
</form> --%>

<!--            Choose you book <select name="ddlBook">
                            <option>Servlet</option>
                            <option>J2EE</option>
                            <option>JavaEE</option>
                            <option>Tomcat</option>
                            <option>JBoss</option>
                            <option>JDBC</option>
                            <option>EJB2</option>
                            <option>EJB3</option>
                            <option>Others</option>
                        </select><br>-->
