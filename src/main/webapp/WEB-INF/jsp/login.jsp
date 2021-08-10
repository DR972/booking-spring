<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in to account</title>
</head>

<body>
    <sec:authorize access="isAuthenticated()">
        <% response.sendRedirect("/"); %>
    </sec:authorize>
    <h2><spring:message code="in" text=""/></h2>
    <div>
        <form method="POST" action="login">
            <div>
                <form method="GET" action="activate/{code}">
                    <input type="hidden" name="code" value="${code}"/>
                    <form:input type="text" path="message" placeholder="message" autofocus="true"></form:input>
                    <form:errors path="message"></form:errors> <spring:message code="${message}" text=""/>
                </form>
                <h4><spring:message code="${error}" text=""/></h4>
                <input name="username" type="text" placeholder="username" autofocus="true"/>
                <input name="password" type="password" placeholder="password"/>
                <button type="submit"><spring:message code="in" text=""/></button>
            </div>
        </form>
        <h4><a href="/registration"><spring:message code="register" text=""/></a></h4>
        <h4><a href="/"><spring:message code="main" text=""/></a></h4>
        <h4><a href="?lang=us"><spring:message code="us" text=""/></a>|<a href="?lang=ru"><spring:message code="ru" text=""/></a></h4>
    </div>
</body>
</html>
