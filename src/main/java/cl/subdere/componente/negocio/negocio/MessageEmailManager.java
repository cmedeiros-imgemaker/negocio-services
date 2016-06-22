package cl.subdere.componente.negocio.negocio;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import cl.subdere.componente.negocio.contracts.SendEmailInput;
import cl.subdere.componente.negocio.exceptions.NegocioException;

/**
 * Message-Driven Bean implementation class for: MessageEmailManager
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue"), @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/queue/SubdereEmail")
		}, 
		mappedName = "java:/queue/SubdereEmail")
public class MessageEmailManager implements MessageListener {

	@Resource
	MessageDrivenContext mdctx;
	
	@EJB
	SenderEmailManager senderEmail;
	
    /**
     * Default constructor. 
     */
    public MessageEmailManager() {
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        ObjectMessage objectMessage;
        try {
        	objectMessage = (ObjectMessage) message;
        	System.out.println("Message Queue: " + objectMessage.getObject().toString());
        	SendEmailInput input = (SendEmailInput) objectMessage.getObject();
        	senderEmail.send(input);
        } catch(JMSException ex) {
        	mdctx.setRollbackOnly();
        	ex.printStackTrace();
        } catch(NegocioException ex) {
        	mdctx.setRollbackOnly();
        	ex.printStackTrace();
        }
    }

}
