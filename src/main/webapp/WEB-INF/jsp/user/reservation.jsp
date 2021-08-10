<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Room Reservation</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>
<body>
    <h3>${pageContext.request.userPrincipal.name}</h3>
	<div align="center">
        <h2><spring:message code="available" text=""/></h2>
        <form action="reservation" method="POST" modelAttribute="form">
            <h3>${form.arrival} - ${form.departure}, <spring:message code="days" text=""/>: ${form.days},
            <spring:message code="persons" text=""/>: ${form.persons}</h3><br/><br/>
        </form>

	    <table>
            <thead>
                <th><spring:message code="rooms" text=""/></th>
                <th><spring:message code="sleeps" text=""/></th>
                <th><spring:message code="price" text=""/></th>
            </thead>
            <br/><h3><spring:message code="${missed}" text=""/></h3><br/>
            <form action="reservation" method="POST" modelAttribute="allRooms">
                <c:forEach var="room" items="${allRooms}">
                    <tr>
                        <td><spring:message code="${room.type}" text=""/></td>
                        <td>${room.sleeps}</td>
                        <td>${room.price}</td>
                        <td>
                            <form action="reservation" method="POST">
                                <input type="hidden" name="roomId" value="${room.number}"/>
                                <button type="submit"><spring:message code="choose" text=""/></button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </form>
        </table>

        <br/><h3><spring:message code="${noAvailable}" text=""/></h3><br/>
        <h4><a href="/"><spring:message code="main"/></a></h4>
        <h4><a href="/user/user"><spring:message code="page"/></a></h4>
        <h4><a href="/user/date"><spring:message code="book"/></a></h4>
        <h4><a href="?lang=us"><spring:message code="us"/></a><a href="?lang=ru"><spring:message code="ru"/></a></h4>
	</div>
</body>
</html>