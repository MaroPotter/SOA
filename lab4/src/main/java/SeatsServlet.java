import pl.labs.Lab4JBL.*;

import javax.ejb.EJB;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SeatsServlet")
public class SeatsServlet extends HttpServlet {
    @EJB
    ApiSeatManagement seatManagement;

    @EJB
    ApiBookingManagement bookingManagement;

    @EJB
    UserManagement userManagement;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!userManagement.UserLogged()){
            response.sendRedirect(request.getRequestURI().replace("/seats","") + "/login.jsp" );
        }
        int row = Integer.parseInt(request.getParameter("row"));
        int column = Integer.parseInt(request.getParameter("column"));
        User user = userManagement.getActUser();
        Payment payment = seatManagement.buyTicket(userManagement.getActUser(),row,column);
        request.setAttribute("username",user.name);
        request.setAttribute("row",row);
        request.setAttribute("column",column);
        request.getRequestDispatcher("/WEB-INF/buy.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int row = Integer.parseInt(request.getParameter("row"));
        int column = Integer.parseInt(request.getParameter("column"));
        Seat seat = bookingManagement.checkSeat(row,column);
        request.setAttribute("row",row);
        request.setAttribute("column",column);
        request.setAttribute("seat",seat);
        request.setAttribute("seatPrice",seatManagement.getSeatPrice(seat));
        request.getRequestDispatcher("/seat.jsp").forward(request,response);
    }
}
