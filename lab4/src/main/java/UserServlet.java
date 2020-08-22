import pl.labs.Lab4JBL.User;
import pl.labs.Lab4JBL.UserManagement;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {

    @EJB
    UserManagement userManagement;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userManagement.LogIn(request.getParameter("login").toString(),request.getParameter("password").toString());
        request.getRequestDispatcher("seats.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }
}
