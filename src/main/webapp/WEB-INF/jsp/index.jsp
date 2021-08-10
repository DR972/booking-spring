<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>Main</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>
<body>
    <h1><spring:message code="greeting" text=""/></h1>
    <div>
        <h3>${pageContext.request.userPrincipal.name}</h3>
        <sec:authorize access="!isAuthenticated()">
            <h4><a href="/login"><spring:message code="in" text=""/></a></h4>
        </sec:authorize>

        <form:form action="/" method="post" modelAttribute="userSession">
            <div>
                <form action="/login" method="post">
                    <input type="hidden" value="Save"/>
                </form>
            </div>
        </form:form>

        <sec:authorize access="isAuthenticated()">
            <h4><a href="/logout"><spring:message code="out" text=""/></a></h4>
        </sec:authorize>

        <sec:authorize access="hasRole('Админ')">
            <h4><a href="/admin/admin" >Главная страница администратора</a></h4>
        </sec:authorize>

        <sec:authorize access="hasRole('Юзер')">
            <h4><a href="/user/user"><spring:message code="page" text=""/></a></h4>
        </sec:authorize>
        <h4><a href="?lang=us"><spring:message code="us" text=""/></a>|<a href="?lang=ru"><spring:message code="ru" text=""/></a></h4>
    </div>
</body>
</html>