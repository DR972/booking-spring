<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page import="by.rozmysl.booking.service.hotelService.RoomService"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Confirmation of the reservation</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>
<body>
    <h3>${pageContext.request.userPrincipal.name}</h3>
	<div align="center">
        <h2><spring:message code="thanks" text=""/></h2>
        <h3><spring:message code="client" text=""/>: ${pageContext.request.userPrincipal.name}</h3>
        <h3><spring:message code="arrival" text=""/>: ${form.arrival}</h3>
        <h3><spring:message code="departure" text=""/>: ${form.departure}</h3>
        <h3><spring:message code="roomType" text=""/>: <spring:message code="${form.room}" text=""/></h3>
        <h3><spring:message code="persons" text=""/>: ${form.persons}</h3>
        <h3><spring:message code="food" text=""/>: <spring:message code="${form.food.type}" text=""/></h3>
        <h3><spring:message code="services" text=""/>: <spring:message code="${form.services.type}" text=""/></h3>
        <h3><spring:message code="amount" text=""/>: ${form.amount}</h3>
	</div>
	<h4><a href="/"><spring:message code="main" text=""/></a></h4>
	<sec:authorize access="hasRole('Админ')">
        <h4><a href="/admin/admin" >Главная страница администратора</a></h4>
    </sec:authorize>
        <h4><a href="/user/user"><spring:message code="page" text=""/></a></h4>
    <h4><a href="?lang=us"><spring:message code="us" text=""/></a>|<a href="?lang=ru"><spring:message code="ru" text=""/></a></h4>
</body>
</html>