<%-- 
    Document   : index
    Created on : Apr 14, 2015, 2:09:50 PM
    Author     : andrii
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PizzaDelivery</title>
    </head>
    <body>
        <h1>Pizza Delivery Start Page</h1>
        Pizzas list: <a href="${pageContext.request.contextPath}/pages/pizza">${pageContext.request.requestURL}pages/pizza</a> <br>
        Orders list: <a href="${pageContext.request.contextPath}/pages/order">${pageContext.request.requestURL}pages/order</a> <br>
    </body>
</html>
