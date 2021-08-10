<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Change room parameters</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>
    <div>
        <h3>Номера</h3>
        <table>
            <thead>
                <th>Номер комнаты</th>
                <th>Тип комнаты</th>
                <th>Кол-во мест</th>
                <th>Изменить параметры</th>
                <th>Цена</th>
                <th>Изменить цену</th>
            </thead>
            <c:forEach items="${rooms}" var="room">
                <div align="center">
                    <tr>
                        <td>${room.number}</td>
                        <td>${room.type}</td>
                        <td>${room.sleeps}</td>
                        <td>
                            <form action="changeRoomParameters" method="POST">
                                <select class="Room" id ="r" name ="room">
                                    <option value="${r.number}">- Выбрать тип -</option>
                                    <c:forEach items="${parameters}" var="p">
                                        <option value = "${p.number}">тип=${p.type}, мест=${p.sleeps}, цена=${p.price}</option>
                                    </c:forEach>
                                    <input type="hidden" name="roomId" value="${room.number}"/>
                                    <input type="hidden" name="changeRoomParameters" value="changeRoomParameters"/>
                                    <button type="submit">Изменить</button>
                                </select>
                            </form>
                        </td>
                        <td>${room.price}</td>
                        <td>
                            <form action="changeRoomParameters" method="post">
                                <input type="number" step="1" name="price" value="${room.price}"></input>
                                <input type="hidden" name="roomId" value="${room.number}"/>
                                <input type="hidden" name="changeRoomPrice" value="changeRoomPrice"/>
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