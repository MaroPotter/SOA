<%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 17.08.2020
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
    <form action="login" method="POST">
        <label>Login: </label><input type="text" name="login"/> </br>
        <label>Password: </label><input type="password" name="password"/> </br>
        <input type="submit" value="Login">
    </form>
    <a href="index.html">Back</a>
</body>
</html>
