<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="by.rozmysl.booking.entity.hotel.StatusReservation"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>All reservations</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>
    <div>
        <table>
            <thead>
                <th>Номер брони</th>
                <th>Номер комнаты</th>
                <th>Изменить номер</th>
                <th>Тип комнаты</th>
                <th>Кол-во мест</th>
                <th>Кол-во гостей</th>
                <th>Дата заезда</th>
                <th>Дата выезда</th>
                <th>Кол-во дней</th>
                <th>Тип питания</th>
                <th>Доп. услуги</th>
                <th>Сумма</th>
                <th>Клиент</th>
                <th>Статус</th>
                <th>Изменить статус</th>
            </thead>
            <form action="allReservation" method="POST" modelAttribute="allReservation">
                <c:forEach items="${allReservation}" var="reservation">
                    <div align="center">
                        <tr>
                            <td>${reservation.id}</td>
                            <td>${reservation.number}</td>
                            <td>
                                <form action="allReservation" method="POST">
                                    <select class="Room" id ="room" name ="room">
                                        <option value="${reservation.number}">- Выбрать номер -</option>
                                        <c:forEach items="${roomService.findFreeRooms(reservation)}" var="r">
                                            <c:if test = "${r.sleeps>=reservation.persons}">
                                                <option value = "${r.number}">номер=${r.number}, тип=${r.type}, мест=${r.sleeps}, цена=${r.price}</option>
                                            </c:if>
                                        </c:forEach>
                                        <input type="hidden" name="reservationId" value="${reservation.id}"/>
                                        <input type="hidden" name="changeRoom" value="changeRoom"/>
                                        <button type="submit">Подтвердить</button>
                                    </select>
                                </form>
                            </td>
                            <td>${reservation.room}</td>
                            <td>${roomService.findByNumber(reservation.number).sleeps}</td>
                            <td>${reservation.persons}</td>
                            <td>${reservation.arrival}</td>
                            <td>${reservation.departure}</td>
                            <td>${reservation.days}</td>
                            <td>${reservation.food.type}</td>
                            <td>${reservation.services.type}</td>
                            <td>${reservation.amount}</td>
                            <td>${reservation.user}</td>
                            <td>${reservation.status}</td>
                            <td>
                                <c:if test = "${roomService.findByNumber(reservation.number) != null}">
                                    <form action="allReservation" method="POST">
                                        <select class="StatusReservation" id ="status" name ="status">
                                            <option value="${reservation.status}">- Изменить -</option>
                                            <c:forEach items="<%=StatusReservation.values()%>" var="st">
                                                <option value = "${st.status}">${st.status}</option>
                                            </c:forEach>
                                            <input type="hidden" name="reservationId" value="${reservation.id}"/>
                                            <input type="hidden" name="changeStatus" value="changeStatus"/>
                                            <button type="submit">Подтвердить</button>
                                        </select>
                                     </form>
                                 </c:if>
                            </td>
                            <td>
                                <form action="allReservation" method="POST">
                                    <input type="hidden" name="reservationId" value="${reservation.id}"/>
                                    <input type="hidden" name="delete" value="delete"/>
                                    <button type="submit">Удалить</button>
                                </form>
                            </td>
                        </tr>
                    </div>
                </c:forEach>
            </form>
        </table>
        <h4><a href="/admin/admin">Главная страница администратора</a></h4>
    </div>
</body>
</html>