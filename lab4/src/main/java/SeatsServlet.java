import pl.labs.Lab4EJB.ApiBookingManagement;
import pl.labs.Lab4EJB.ApiSeatManagement;
import pl.labs.Lab4EJB.User;
import pl.labs.Lab4EJB.UserManagement;
import pl.labs.Lab4EJB.Payment;
import pl.labs.Lab4EJB.Seat;



import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        request.setAttribute("payment", payment);
        request.setAttribute("username",user.name);
        request.setAttribute("row",row);
        request.setAttribute("column",column);
        request.getRequestDispatcher("buy.jsp").forward(request,response);
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
