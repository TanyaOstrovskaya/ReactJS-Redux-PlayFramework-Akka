package mail;

import com.google.gson.Gson;
import org.apache.activemq.ActiveMQConnectionFactory;
import play.Logger;
import play.api.Play;
import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;

import javax.inject.Inject;
import javax.jms.*;

public class MailConsumer implements Runnable, ExceptionListener {

    private static Thread mailConsumerService;

    public static synchronized void initService() {
        MailConsumer mailConsumer = Play.current().injector().instanceOf(MailConsumer.class);
        if (mailConsumerService != null) {
            System.out.println("STOPPING MailConsumer thread.");
            mailConsumerService.interrupt();
        }
        System.out.println("Starting MailConsumer thread.");
        mailConsumerService = new Thread(mailConsumer);
        mailConsumerService.setDaemon(true);
        mailConsumerService.setName("MailConsumer Service");
        mailConsumerService.start();
        System.out.println("MailConsumer thread started.");
    }

    @Inject
    private MailerClient mailerClient;

    @Override
    public void run() {
        try {
            // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(MailProducer.BROKER_URL);

            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();

            connection.setExceptionListener(this);

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue(MailProducer.AMQ_MAIL_QUEUE);

            // Create a MessageConsumer from the Session to the Topic or Queue
            MessageConsumer consumer = session.createConsumer(destination);

            while (!Thread.currentThread().isInterrupted()) {
                // Wait for a message
                Message message = consumer.receive();

                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    String text = textMessage.getText();
                    System.out.println("Received: " + text);
                    Email mail = new Gson().fromJson(text, Email.class);
                    Email email = new Email();
                    email.setFrom(mail.getFrom());
                    email.setTo(mail.getTo());
                    email.setSubject(mail.getSubject());
                    email.setBodyHtml(mail.getBodyHtml());
                    System.out.println("sending email...");
                    mailerClient.send(email);
                    System.out.println("email sent!");
                } else {
                    System.out.println("Received: " + message);
                    System.out.println("message type: "+message.getClass().getSimpleName());
                }

            }
            System.out.println("MailConsumer interrupted.");
            consumer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            if (e instanceof InterruptedException) {
                System.out.println("MailConsumer thread interrupted.");
            } else {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }

    public synchronized void onException(JMSException ex) {
        System.out.println("JMS Exception occured.  Shutting down client.");
        System.out.println("ErrorCode=" + ex.getErrorCode() + " , " + ex.getMessage());
    }
}