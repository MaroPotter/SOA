<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="resources/style.css">
</head>
<body>
    <h1>Logging in</h1>
    <form action="login" method="POST">
        <label>Login: </label><input type="text" name="login"/>
        <label>Password: </label><input type="password" name="password"/>
        </br>
        <input type="submit" value="Sign in" id="submitt">
    </form>
    <a href="index.html" id="back">Back</a>
</body>
</html>
