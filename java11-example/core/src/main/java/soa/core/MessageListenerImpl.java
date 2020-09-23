package soa.core;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/DLQ"),
        }
)

public class MessageListenerImpl implements MessageListener {

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = null;
        try{
            if(message instanceof TextMessage){
                textMessage = (TextMessage) message;
                String txt = textMessage.getText();
                System.out.println("\n\nZAKOŃCZONO DODAWANIE REKORDU ID: " + txt + "\n\n");
            }
        }
        catch(Exception e){
            System.out.println("Error on receiving message: \n");
            e.printStackTrace();
        }
    }
}