<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>All users</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>
    <div>
        <table>
            <thead>
                <th>ID</th>
                <th>Пользователь</th>
                <th>Пароль</th>
                <th>Почта</th>
                <th>Активация</th>
                <th>Роли</th>
            </thead>
            <form action="allUsers" method="post" modelAttribute="allUsers">
                <c:forEach items="${allUsers}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.password}</td>
                        <td>${user.email}</td>
                        <td>${user.active}</td>
                        <td>
                            <c:forEach items="${user.roles}" var="role">${role.role}; </c:forEach>
                        </td>

                        <td>
                            <form action="allUsers" method="post">
                                <input type="hidden" name="userId" value="${user.id}"/>
                                <input type="hidden" name="delete" value="delete"/>
                                <button type="submit">Удалить</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </form>
        </table>
        <h4><a href="/admin/admin">Главная страница администратора</a></h4>
        <h4><a href="/">Главная</a></h4>
    </div>
</body>
</html>