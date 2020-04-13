<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All users</title>
</head>

<body>
<div>
    <jsp:useBean id="allUsers" class="service.UserService"/>
    <c:set var="users" value="${allUsers.allUsers}"/>

    <c:forEach items="${users}" var="user">
        <c:out value="${user.name}"/>
        <c:out value="${user.password}"/>
        <c:out value="${user.age}"/><br>
        <form method="post">
            <button name="id" value="${user.id}" formaction="/delete">Delete</button>
            <button name="id" value="${user.id}" formmethod="get" formaction="/update">Update</button>
        </form>
    </c:forEach>
</div>
<form action="/add" method="post">
    Name:<br>
    <input type="text" name="name"><br><br>
    Password:<br>
    <input type="text" name="password"><br><br>
    Age:<br>
    <input type="number" name="age"><br>
    <button>Let's go...</button>
</form>
</body>
</html>