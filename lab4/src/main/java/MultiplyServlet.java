import pl.labs.Lab4EJB.ApiCounter;
import pl.labs.Lab4EJB.ApiMultiply;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MultiplyServlet")
public class MultiplyServlet extends HttpServlet {

    @EJB
    private ApiMultiply multiply;

    @EJB
    private ApiCounter counter;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int a = Integer.parseInt(request.getParameter("a"));
        int b = Integer.parseInt(request.getParameter("b"));
        int c = multiply.multiply(a,b);
        counter.inc();
        PrintWriter out = response.getWriter();
        out.println("Ecclesia, it's a result: " + c);
        out.close();
    }
}
