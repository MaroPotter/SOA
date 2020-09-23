package soa.soap;

import org.jboss.annotation.security.SecurityDomain;
import org.jboss.ws.api.annotation.WebContext;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.concurrent.ThreadLocalRandom;

import soa.db.MessageDaoImpl;

@Stateless
@WebService
@SecurityDomain("Test-security-domain")
@DeclareRoles({"GrupaTestowa"})
@WebContext(contextRoot = "/soa", urlPattern = "/service",
            authMethod = "BASIC", transportGuarantee = "NONE",
            secureWSDLAccess = false)

public class WS {
    private MessageDaoImpl messageDao = new MessageDaoImpl();

    @EJB
    private MessageSender messageSender = new MessageSender();

    @PermitAll
    @WebMethod
    public long push(@WebParam(name = "data") String data){
        long id = messageDao.add(data);
        action(id);
        messageDao.update(id);
        return id;
    }

    @RolesAllowed("GrupaTestowa")
    @WebMethod
    public boolean check(@WebParam(name = "id") long id){
        try {
            return !messageDao.searchById(id).getTime_receive().isEmpty();
        }
        catch(NullPointerException e){
            System.out.println("The null pointer exception for id: " + id + "\n");
            e.printStackTrace();
            return false;
        }
    }

    private void action(long id){
        int minTime = 6000;
        int maxTime = 7000;

        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(minTime, maxTime + 1));
        } catch (InterruptedException e) {
            System.out.println("Preemption of the thread unsuccessful: \n");
            e.printStackTrace();
        }
        messageSender.sendMessage(String.valueOf(id));
    }
}
