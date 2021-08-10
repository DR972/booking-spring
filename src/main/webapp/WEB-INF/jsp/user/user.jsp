<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>User page</title>
</head>
<body>
<div>
    <h2><spring:message code="page" text="default"/></h2>
    <h4><a href="/user/date"><spring:message code="book" text=""/></a></h4>
    <h4><a href="/user/price"><spring:message code="prices" text=""/></a></h4>
    <h4><a href="/user/userReservation"><spring:message code="myReservations" text=""/></a></h4>
    <h4><a href="/"><spring:message code="main" text=""/></a></h4>
    <h4><a href="?lang=us"><spring:message code="us" text=""/></a>|<a href="?lang=ru"><spring:message code="ru" text=""/></a></h4>
</div>
</body>
</html>