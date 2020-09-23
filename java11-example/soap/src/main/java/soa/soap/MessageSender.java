package soa.soap;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Stateless
public class MessageSender {

    @Resource(mappedName = "java:/jms/queue/DLQ")
    private Queue queueTest;

    @Inject
    private JMSContext jmsContext;

    public void sendMessage(String txt) {
        try {
            jmsContext.createProducer().send(queueTest, txt);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}