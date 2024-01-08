<%-- 
    Document   : checkout
    Created on : Oct 29, 2023, 3:33:24 PM
    Author     : tahoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CHECK OUT PAGE</title>
    </head>
    <body>
        <h1>CHECK OUT PAGE</h1>
        <c:set var="order" value="${requestScope.ORDER_RESULT}"/>
        <c:if test="${not empty order}">
            OrderID: <font>${order.id}</font><br>
            Date: <font>${order.oDate}</font>
            <c:set var="orderDetailResult" value="${requestScope.ORDERDETAIL_RESULT}"/>
            <c:if test="${not empty orderDetailResult}">

                <table border="1">
                    <thead>
                        <tr>
                            <th>OrderDetail ID</th>
                            <th>Product ID</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>OrderDetail Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="orderDetailDTO" items="${orderDetailResult}">
                            <tr>
                                <td>
                                    ${orderDetailDTO.id}
                                </td>
                                <td>
                                    ${orderDetailDTO.productID}
                                </td>
                                <td>
                                    ${orderDetailDTO.quantity}
                                </td>
                                <td>
                                    ${orderDetailDTO.price}
                                </td>
                                <td>
                                    ${orderDetailDTO.total}
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="4" >
                                Total
                            </td>
                            <td>
                                ${order.total}
                            </td>                              
                        </tr>
                    </tbody>
                </table>
                            <font color="red">THANKS FOR YOUR SHOPPING</font><br>
                            <a href="BookStoreServlet">Click here to continue shopping</a>
            </c:if>
        </c:if>
        <c:if test="${empty order}">
            <h2 style="color: red">No Any Check Out!!!</h2>
        </c:if>
    </body>
</html>
