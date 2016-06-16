package cl.subdere.componente.negocio.negocio;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import cl.subdere.componente.negocio.contracts.SendEmailInput;
import cl.subdere.componente.negocio.dto.MessageBody;
import cl.subdere.componente.negocio.exceptions.NegocioException;
import cl.subdere.componente.negocio.jms.entities.QueueProducer;

/**
 * Session Bean implementation class SenderEmailManager
 */
@Stateless
@LocalBean
public class SenderEmailManager implements SenderEmailManagerRemote, SenderEmailManagerLocal {

	public static final String EMAIL_SESSION_JNDI_PATH = System.getProperty("mail.smtp.jndi", "java:jboss/mail/Subdere");
	
    /**
     * Default constructor. 
     */
    public SenderEmailManager() {
    }

	@Override
	public Boolean send(SendEmailInput input) throws NegocioException {
		try {
			System.out.println("1st ===> Get Mail Server Session..");
			MimeMessage message = new MimeMessage(getMailSession());
			System.out.println("2st ===> Generate mail message..");
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(input.getTo()));
			message.setSubject(input.getSubject());
			message.setSentDate(new java.util.Date());
			message.setText(this.getMessageBody(input.getType()), "utf-8", "html");
			System.out.println("3st ===> Send mail message..");
            Transport.send(message);
            return true;
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean sendAsync(SendEmailInput input) throws NegocioException {
		System.out.println("Start sendAsync: " + input.toString());
		QueueProducer queueProducer = new QueueProducer();
		try {
			queueProducer.sendMessage(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	private Session getMailSession() throws NamingException {
		InitialContext context = new InitialContext();
		System.out.println("EMAIL_SESSION_JNDI_PATH: " + EMAIL_SESSION_JNDI_PATH);
        return (Session) context.lookup(EMAIL_SESSION_JNDI_PATH);
	}
	
	private String getMessageBody(Integer type) {
		MessageBody messageFormat = MessageBody.getByNumero(type);
		return messageFormat.getContent();
	}
}
