<%@ page import="pl.labs.Lab4EJB.Payment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>"Seat ${row}-${column}</title>
    <link rel="stylesheet" href="resources/style.css">
</head>
<body>
<%
    Payment payment = (Payment) request.getAttribute("payment");
    if(payment == null) {
%>
<h3> Oops! </h3>
<p>  You don't have enough funds in your account!
    You can't afford to book this seat in the theater. </br>
</p>
<%
    } else {
%>
<h3> Seat ${row}-${column}</h3>
    <p> This seat is booked by ${username}. </br>
    <i> Thank you!</i> </p>
<%
    }
%>
    <a href="seats.jsp" id="back">Back</a>
</body>
</html>
