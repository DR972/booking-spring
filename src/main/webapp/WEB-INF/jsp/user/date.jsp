<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Room Reservation Form</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/reserv.css">
</head>
<body>
    <h3>${pageContext.request.userPrincipal.name}</h3>
	<div align="center">
		<h2><spring:message code="book" text=""/></h2>
        <form:form action="date" method="POST" modelAttribute="form">
            <form:label path="arrival"><spring:message code="arrival" text=""/>:</form:label>
            <form:input path="arrival" type="date"></form:input>
            <form:errors path="arrival"><spring:message code="arrivalError"/></form:errors><spring:message code="${dateError}" text=""/><br/>

            <form:label path="days"><spring:message code="days" text=""/>:</form:label>
            <form:input path="days" type="number" min="1" max="30"></form:input>
            <form:errors path="days"><spring:message code="reservationError"/></form:errors><br/>

            <form:label path="persons"><spring:message code="persons" text=""/>:</form:label>
            <form:input path="persons" type="number" min="1" max="10"/>
            <form:errors path="persons"><spring:message code="reservationError"/></form:errors><br/>

            <form:label path="food"><spring:message code="food"/>:</form:label>
            <form:select path="food">
                <c:forEach  items="${food}" var="food">
                    <option value="${food.type}"><spring:message code="${food.type}" text=""/></option>
                </c:forEach>
            </form:select><br/>

            <form:label path="services"><spring:message code="services" text=""/>:</form:label>
            <form:select path="services">
                <c:forEach  items="${services}" var="service">
                    <option value="${service.type}"><spring:message code="${service.type}" text=""/></option>
                </c:forEach>
            </form:select><br/>

            <button type="submit"><spring:message code="search" text=""/></button>
		</form:form>
		<h4><a href="/"><spring:message code="main" text=""/></a></h4>
		<h4><a href="/user/user"><spring:message code="page" text=""/></a></h4>
        <h4><a href="?lang=us"><spring:message code="us" text=""/></a>|<a href="?lang=ru"><spring:message code="ru" text=""/></a></h4>
	</div>
</body>
</html>