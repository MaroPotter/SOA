<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: marek
  Date: 12.04.2020
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Aplikacja Webowa</title>
  </head>
  <body>
    <h1> To jest moja pierwsza aplikacja webowa w JavaEE</h1>
    <p> Wyświetlam na razie stronę statyczną </p>
    <%
        Date tmp = new Date();
        out.println("<h2>" + tmp.toString() + "</h2>");
    %>
  </body>
</html>
