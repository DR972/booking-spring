<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Change the prices of services</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>
    <div>
        <h3>Питание</h3>
        <table>
            <thead>
                <th>Тип питания</th>
                <th>Цена</th>
                <th>Изменить цену</th>
            </thead>
            <c:forEach items="${food}" begin="1" var="food">
                <div align="center">
                    <tr>
                        <td>${food.type}</td>
                        <td>${food.price}</td>
                        <td>
                            <form action="changeServicesPrice" method="post">
                                <input type="number" step="0.5" name="foodPrice" value="${food.price}"></input>
                                <input type="hidden" name="foodId" value="${food.type}"/>
                                <input type="hidden" name="changeFoodPrice" value="changeFoodPrice"/>
                                <button type="submit">Изменить</button>
                            </form>
                        </td>
                    </tr>
                </div>
            </c:forEach>
        </table>

        <h3>Дополнительные услуги</h3>
        <table>
            <thead>
                <th>Тип услуги</th>
                <th>Цена</th>
                <th>Изменить цену</th>
            </thead>
            <c:forEach items="${services}" begin="1" var="service">
                <div align="center">
                    <tr>
                        <td>${service.type}</td>
                        <td>${service.price}</td>
                        <td>
                            <form action="changeServicesPrice" method="post">
                                <input type="number" step="0.5" name="servicePrice" value="${service.price}"></input>
                                <input type="hidden" name="serviceId" value="${service.type}"/>
                                <input type="hidden" name="changeServicePrice" value="changeServicePrice"/>
                                <button type="submit">Изменить</button>
                            </form>
                        </td>
                    </tr>
                </div>
            </c:forEach>
        </table>
        <h4><a href="/admin/admin">Главная страница администратора</a></h4>
    </div>
</body>
</html>