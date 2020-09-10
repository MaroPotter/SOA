import pl.labs.Lab4EJB.User;
import pl.labs.Lab4EJB.UserManagement;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserInfoServlet")
public class UserInfoServlet extends HttpServlet {
    @EJB
    UserManagement userManagement;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!userManagement.UserLogged()){
            response.sendRedirect(request.getRequestURI().replace("/userinfo","") + "/login.jsp" );
        }
        User user = userManagement.getActUser();
        request.setAttribute("username",user.name);
        request.setAttribute("account", user.accountMoney);
        request.getRequestDispatcher("/userinfo.jsp").forward(request, response);


    }
}
