<%@ page import="pl.labs.Lab4EJB.ApiSeatManagement" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="resources/style.css">

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
    <a href="index.html" id="back">Back</a>
    <script>

</script>
</body>
</html>
