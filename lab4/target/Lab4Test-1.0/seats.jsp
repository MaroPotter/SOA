<%@ page import="pl.labs.Lab4JBL.ApiSeatManagement" %><%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 14.08.2020
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>Seats</h2>
    <table>
        <%
            for(int i=0;i<10;i++)
            {
                out.print("<tr>");
                for(int j=0;j<12;j++)
                {
                    out.print("<td><a href='seats?row=" + i + "&column=" + j + "'>" + i + "-" + j + "</a></td>");
                }
                out.print("</tr>");
            }
        %>
    </table>
<script>

</script>
</body>
</html>
