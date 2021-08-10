<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>All rooms</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>
    <div>
        <table>
            <thead>
                <th>Номер номера</th>
                <th>Тип номера</th>
                <th>Вместимость</th>
                <th>Цена</th>
                <th>Изменить цену</th>
            </thead>
            <form action="allRooms" method="POST" modelAttribute="allRooms">
                <c:forEach items="${allRooms}" var="room">
                    <tr>
                        <td>${room.number}</td>
                        <td>${room.type}</td>
                        <td>${room.sleeps}</td>
                        <td>${room.price}</td>
                        <td>
                            <form action="allRooms" method="POST">
                                <input type="number" step="1" name="price" value="${room.price}"></input>
                                <input type="hidden" name="roomId" value="${room.number}"/>
                                <input type="hidden" name="changePrice" value="changePrice"/>
                                <button type="submit">Изменить</button>
                            </form>
                        </td>
                        <td>
                            <form action="allRooms" method="POST">
                                <input type="hidden" name="roomId" value="${room.number}"/>
                                <input type="hidden" name="delete" value="delete"/>
                                <button type="submit">Удалить</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </form>
        </table>
        <h4><a href="/admin/admin">Главная страница администратора</a></h4>
        <h4><a href="/">Главная</a></h4>
    </div>
</body>
</html>