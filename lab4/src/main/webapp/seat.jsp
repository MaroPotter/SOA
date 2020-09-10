<%@ page import="pl.labs.Lab4EJB.Seat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Seat ${row}-${column}</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="resources/style.css">

</head>
<body>
    <%
        Seat seat = (Seat) request.getAttribute("seat");
        String description = "";
        if(seat.booked)
            description = "not ";

    %>
    <h3>Seat ${row}-${column}</h3>
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
<a href="seats.jsp" id="back">Back</a>
</body>
</html>
