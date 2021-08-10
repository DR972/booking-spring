<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="java.time.LocalDate" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>All user reservations</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>
    <div>
        <table>
            <thead>
                <th><spring:message code="reservationNumber" text=""/></th>
                <th><spring:message code="roomType" text=""/></th>
                <th><spring:message code="persons" text=""/></th>
                <th><spring:message code="arrival" text=""/></th>
                <th><spring:message code="departure" text=""/></th>
                <th><spring:message code="days" text=""/></th>
                <th><spring:message code="food" text=""/></th>
                <th><spring:message code="services" text=""/></th>
                <th><spring:message code="amount" text=""/></th>
            </thead>
            <form action="userReservation" method="POST" modelAttribute="userReservation">
                <c:forEach items="${userReservation}" var="reservation">
                    <div align="center">
                        <tr>
                            <td>${reservation.id}</td>
                            <td><spring:message code="${reservation.room}" text=""/></td>
                            <td>${reservation.persons}</td>
                            <td>${reservation.arrival}</td>
                            <td>${reservation.departure}</td>
                            <td>${reservation.days}</td>
                            <td><spring:message code="${reservation.food.type}" text=""/></td>
                            <td><spring:message code="${reservation.services.type}" text=""/></td>
                            <td>${reservation.amount}</td>
                            <td>
                                <c:if test = "${LocalDate.now().isBefore(reservation.arrival)}">
                                    <form action="userReservation" method="POST">
                                        <input type="hidden" name="reservationId" value="${reservation.id}"/>
                                        <input type="hidden" name="delete" value="delete"/>
                                        <button type="submit"><spring:message code="delete" text=""/></button>
                                    </form>
                                </c:if>
                            </td>
                        </tr>
                    </div>
                </c:forEach>
            </form>
        </table>
        <h4><a href="/user/user"><spring:message code="page"/></a></h4>
        <h4><a href="/"><spring:message code="main" text=""/></a></h4>
        <h4><a href="?lang=us"><spring:message code="us" text=""/></a>|<a href="?lang=ru"><spring:message code="ru" text=""/></a></h4>
    </div>
</body>
</html>