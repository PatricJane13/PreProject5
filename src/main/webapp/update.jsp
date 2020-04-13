<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update</title>
</head>

<body >
        <form action="/update" method="post" ${requestScope.user}>
            OldLogin:<br>
            <input value="${requestScope.user.name}" type="text" name="oldName"><br><br>
            OldPassword:<br>
            <input value="${requestScope.user.password}" type="text" name="oldPassword"><br><br>
            OldAge:<br>
            <input value="${requestScope.user.age}" type="number" name="oldAge"><br><br>
            NewLogin:<br>
            <input type="text" name="newName"><br><br>
            NewPassword:<br>
            <input type="text" name="newPassword"><br><br>
            NewAge:<br>
            <input type="number" name="newAge"><br>
            <button>Let's go update...</button>
        </form>
</body>
</html>
