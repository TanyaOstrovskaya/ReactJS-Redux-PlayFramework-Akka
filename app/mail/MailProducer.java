package mail;

import com.google.gson.Gson;
import org.apache.activemq.ActiveMQConnectionFactory;
import play.libs.mailer.Email;
import javax.jms.*;

public class MailProducer implements Runnable{
    public static final String AMQ_MAIL_QUEUE = "MAIL";
    public static final String BROKER_URL = "vm://localhost?broker.useJmx=false&persistent=false";
    private Email mail;

    public MailProducer(Email mail) {
        this.mail = mail;
    }

    public static void sendMail(Email mail){
        Thread brokerThread = new Thread(new MailProducer(mail));
        brokerThread.setDaemon(false);
        brokerThread.start();
    }

    @Override
    public void run() {
        try {
            // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);

            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue(AMQ_MAIL_QUEUE);

            // Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // Create a messages
            TextMessage textMessage = session.createTextMessage(new Gson().toJson(mail));
            // Tell the producer to send the message
            System.out.println("Sent message: "+ new Gson().toJson(mail) + " : " + Thread.currentThread().getName());
            producer.send(textMessage);

            // Clean up
            session.close();
            connection.close();
        }
        catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }
}