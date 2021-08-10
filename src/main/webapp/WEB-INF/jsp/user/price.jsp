<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Price</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>
    <div>
        <h3><spring:message code="rooms" text=""/></h3>
        <table>
            <thead>
                <th><spring:message code="rooms" text=""/></th>
                <th><spring:message code="sleeps" text=""/></th>
                <th><spring:message code="price" text=""/></th>
            </thead>
            <form method="GET" modelAttribute="allTypeRooms">
                <c:forEach items="${allTypeRooms}" var="room">
                    <tr>
                        <td><spring:message code="${room.type}" text=""/></td>
                        <td>${room.sleeps}</td>
                        <td>${room.price}</td>
                    </tr>
                </c:forEach>
            </form>
        </table>
        <h3><spring:message code="food" text=""/></h3>
        <table>
            <thead>
                <th><spring:message code="food" text=""/></th>
                <th><spring:message code="price" text=""/></th>
            </thead>
            <form method="GET" modelAttribute="allTypeFoods">
                <c:forEach items="${allTypeFoods}" begin="1" var="food">
                    <tr>
                        <td><spring:message code="${food.type}" text=""/></td>
                        <td>${food.price}</td>
                    </tr>
                </c:forEach>
            </form>
        </table>
        <h3><spring:message code="services" text=""/></h3>
        <table>
            <thead>
                <th><spring:message code="services" text=""/></th>
                <th><spring:message code="price" text=""/></th>
            </thead>
            <form method="GET" modelAttribute="allServices">
                <c:forEach items="${allServices}" begin="1" var="service">
                    <tr>
                        <td><spring:message code="${service.type}" text=""/></td>
                        <td>${service.price}</td>
                    </tr>
                </c:forEach>
            </form>
        </table>
        <sec:authorize access="hasRole('Админ')">
            <h4><a href="/admin/admin" >Главная страница администратора</a></h4>
        </sec:authorize>
        <h4><a href="/user/user"><spring:message code="page" text=""/></a></h4>
        <h4><a href="/user/date"><spring:message code="book" text=""/></a></h4>
        <h4><a href="/"><spring:message code="main" text=""/></a></h4>
        <h4><a href="?lang=us"><spring:message code="us" text=""/></a>|<a href="?lang=ru"><spring:message code="ru" text=""/></a></h4>
    </div>
    </body>
</html>