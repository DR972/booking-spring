<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Registration</title>
</head>

<body>
    <div>
        <form:form method="POST" modelAttribute="userForm">
            <h2><spring:message code="registration" text="default"/></h2>
            <div>
                <form:input type="text" path="username" placeholder="Username" autofocus="true"></form:input>
                <form:errors path="username"><spring:message code="userError"/></form:errors><spring:message code="${usernameError}" text=""/>
            </div>
            <div>
                <form:input type="password" path="password" placeholder="Password"></form:input>
            </div>
            <div>
                <form:input type="password" path="passwordConfirm" placeholder="Confirm your password"></form:input>
                <form:errors path="password"><spring:message code="userError"/></form:errors><spring:message code="${passwordError}" text=""/>
            </div>
            <div>
                 <form:input type="email" path="email" placeholder="some@some.com"></form:input>
                 <form:errors path="email"><spring:message code="emailError"/></form:errors><br/>
            </div>
            <button type="submit"><spring:message code="register" text=""/></button>
        </form:form>
        <h4><a href="/"><spring:message code="main" text=""/></a></h4>
        <h4><a href="?lang=us"><spring:message code="us" text=""/></a>|<a href="?lang=ru"><spring:message code="ru" text=""/></a></h4>
    </div>
</body>
</html>