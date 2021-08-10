<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>Error page</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>
<body>
    <h1><spring:message code="error" text=""/></h1>
    <div>
        <h3>${pageContext.request.userPrincipal.name}</h3>
        <sec:authorize access="hasRole('Админ')">
            <h4><a href="/admin/admin" >Главная страница администратора</a></h4>
        </sec:authorize>
        <sec:authorize access="hasRole('Юзер')">
            <h4><a href="/user/user"><spring:message code="page" text="default"/></a></h4>
        </sec:authorize>
        <h4><a href="/"><spring:message code="main" text="default"/></a></h4>
        <h4><a href="?lang=us"><spring:message code="us" text=""/></a>|<a href="?lang=ru"><spring:message code="ru" text=""/></a></h4>
    </div>
</body>
</html>