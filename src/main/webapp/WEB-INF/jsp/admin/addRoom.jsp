<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add a room</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/reserv.css">
</head>
<body>
	<div align="center">
		<h3>Добавить номер</h3>
        <form:form action="addRoom" method="POST" modelAttribute="formRoom">
            <form:label path="number">Номер комнаты</form:label>
            <form:input path="number" type="number" min="1"></form:input>
            <form:errors path="number"></form:errors>${numberError}<br/>

            <form:label path="type">Тип</form:label>
            <form:select path="type" required="required">
                  <form:option value="" label="- Выбрать -"/>
                  <form:options items="${room}"/>
            </form:select>
            <form:errors path="type"></form:errors>${typeError}<br/>

            <form:label path="sleeps">Кол-во мест</form:label>
            <form:input path="sleeps" type="number" min="1"></form:input>
            <form:errors path="sleeps"></form:errors>${sleepsError}<br/>

            <c:if test = "${!roomService.checkRoomByTypeAndSleeps(type,sleeps)}">
                <form:label path="price">Цена</form:label>
                <form:input path="price" type="number" min="1"/><br/>
            </c:if>

            <button type="submit">Сохранить</button>
		</form:form>
		<h4><a href="/admin/admin">Главная страница администратора</a></h4>
	</div>
</body>
</html>