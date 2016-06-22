package cl.subdere.componente.negocio.jms.entities;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.ObjectMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import cl.subdere.componente.negocio.contracts.SendEmailInput;

public class QueueProducer {

    private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String DEFAULT_DESTINATION = "/jms/queue/SubdereEmail";
    private static final String DEFAULT_USERNAME = "quickstartUser";
    private static final String DEFAULT_PASSWORD = "quickstartPwd1!";
    private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    private static final String PROVIDER_URL = "remote://localhost:4447";
	
    public void sendMessage(SendEmailInput input) throws NamingException, JMSException {
    	Context context = null;
    	Connection connection = null;
    	
    	final Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, 
        		System.getProperty(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY));
        System.out.println("INITIAL_CONTEXT_FACTORY: " + System.getProperty(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY));
        env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
        System.out.println("PROVIDER_URL: " + System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
        env.put(Context.SECURITY_PRINCIPAL, System.getProperty("jms.username", DEFAULT_USERNAME));
        System.out.println("jms.username: " + System.getProperty("jms.username", DEFAULT_USERNAME));
        env.put(Context.SECURITY_CREDENTIALS, System.getProperty("jms.password", DEFAULT_PASSWORD));
        env.put("jboss.naming.client.ejb.context", true);
        env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        context = new InitialContext(env);
        
        String connectionFactoryString = System.getProperty("connection.factory", DEFAULT_CONNECTION_FACTORY);
        ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup(connectionFactoryString);
        String destinationString = System.getProperty("destination", DEFAULT_DESTINATION);
        Destination destination = (Destination) context.lookup(destinationString);
        
        connection = connectionFactory.createConnection(
        		System.getProperty("jms.username", DEFAULT_USERNAME), 
        		System.getProperty("jms.password", DEFAULT_PASSWORD)
		);
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(destination);
        
        connection.start();
        
    	ObjectMessage message = session.createObjectMessage(input);
        producer.send(message);
	
		if (context != null) {
            context.close();
        }

        if (connection != null) {
            connection.close();
        }
    }
    
    public void sendMessage2(SendEmailInput input) throws NamingException, JMSException {
    	InitialContext ic = new InitialContext();
    	ConnectionFactory cf = (ConnectionFactory)ic.lookup("/ConnectionFactory");
    	Queue emailQueue = (Queue)ic.lookup("/jms/queue/SubdereEmail");
    	Connection connection = cf.createConnection();
    	Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    	MessageProducer producer = session.createProducer(emailQueue);
    	connection.start();
    	ObjectMessage message = session.createObjectMessage(input);
    	producer.send(message);
    	connection.close();
    	session.close();
    }
}
