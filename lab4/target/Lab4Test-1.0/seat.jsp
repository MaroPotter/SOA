<%@ page import="pl.labs.Lab4JBL.Seat" %><%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 17.08.2020
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Seat seat = (Seat) request.getAttribute("seat");
    String description = "";
    if(seat.booked)
        description = "not ";

%>
<html>
<head>
    <title>Seat + ${row} + ${column}</title>
    <meta charset="UTF-8"/>
</head>
<body>
    <h2>Seat + ${row} + ${column}</h2>
    <p> This seat is <%out.print(description);%> available for booking </br>
        Price: <i>${seatPrice}</i>
    </p>
    <%
        if(!seat.booked)
        {
          out.print("<form action='seats' method='POST'>" +
              "<input type='hidden' name='row' value='" + seat.row + "' />" +
              "<input type='hidden' name='column' value='" + seat.column +"' />" +
              "<input type='submit' value='Book' />" + "</form>");
        }
    %>
<a href="seats.jsp">Back</a>
</body>
</html>
