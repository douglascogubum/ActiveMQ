package br.com.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.naming.InitialContext;

public class TesteConsumer {

	public static void main(String[] args) throws Exception {
		
		InitialContext contex = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory)contex.lookup("ConnectionFactory");
		
		Connection connection = factory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination fila = (Destination)contex.lookup("financeiro");
		MessageConsumer consumer = session.createConsumer(fila); 
		
		Message message = consumer.receive();
		
		System.out.println("Recebendo msg: " + message);	
		
		connection.close();
		contex.close();
	}
}
