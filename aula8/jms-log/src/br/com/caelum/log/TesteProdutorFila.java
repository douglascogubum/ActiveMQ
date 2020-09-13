package br.com.caelum.log;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;

public class TesteProdutorFila {

	public static void main(String[] args) throws Exception {
		
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		
		Connection connection = factory.createConnection("user", "senha"); 
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination fila = (Destination) context.lookup("LOG");
		
		MessageProducer producer = session.createProducer(fila);
		
		Message message = session.createTextMessage("DEBUG | Stopping tcp://127.0.0.1:51222 because Failed with SecurityException: User name [null] or password is invalid.");
		producer.send(message, DeliveryMode.NON_PERSISTENT, 0, 50000);
		
		message = session.createTextMessage("INFO | Stopping tcp://127.0.0.1:51222 because Failed with SecurityException: User name [null] or password is invalid.");
		producer.send(message, DeliveryMode.NON_PERSISTENT, 3, 50000);
		
		message = session.createTextMessage("ERROR | Stopping tcp://127.0.0.1:51222 because Failed with SecurityException: User name [null] or password is invalid.");
		producer.send(message, DeliveryMode.NON_PERSISTENT, 9, 50000);
		
		message = session.createTextMessage("WARN | Stopping tcp://127.0.0.1:51222 because Failed with SecurityException: User name [null] or password is invalid.");
		producer.send(message, DeliveryMode.NON_PERSISTENT, 7, 50000);
		
		session.close();
		connection.close();
		context.close();
	}
}
