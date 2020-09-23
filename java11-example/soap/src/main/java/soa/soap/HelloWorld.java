package soa.soap;

import org.jboss.ws.api.annotation.WebContext;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@Stateless
@WebService
@WebContext(contextRoot="/soa", urlPattern="/hello")

@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public class HelloWorld {

    @WebMethod(action="hello")
    @WebResult(name="result")
    public String Hi(@WebParam(name = "name") String name) {
        return "Hello " + name;
    }

}
