package actors;

import akka.NotUsed;
import akka.actor.AbstractActor;
import akka.stream.Materializer;
import akka.stream.alpakka.jms.JmsSinkSettings;
import akka.stream.alpakka.jms.JmsSourceSettings;
import akka.stream.alpakka.jms.javadsl.JmsSink;
import akka.stream.alpakka.jms.javadsl.JmsSource;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import email.SendMailTLS;
import org.apache.activemq.ActiveMQConnectionFactory;
import java.util.*;
import java.util.concurrent.CompletionStage;

public class MailActor extends AbstractActor {

    private Materializer mat;
    private SendMailTLS mailer;

    public MailActor (Materializer materializer) {
        this.mat = materializer;
        this.mailer = new SendMailTLS();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, str -> {
                    sendToQueue(str);
                    receiveFromQueue();
                    sender().tell(str, self());
                })
                .build();
    }

    private void sendToQueue(String message)  {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Sink<String, NotUsed> jmsSink = JmsSink.textSink(
                JmsSinkSettings
                        .create(connectionFactory)
                        .withQueue("Orders")
        );
        List<String> in = Arrays.asList(message);
        Source.from(in).runWith(jmsSink, mat);
        System.out.println("sent:" + message);
    }

    private void receiveFromQueue () {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Source<String, NotUsed> jmsSource = JmsSource
                .textSource(JmsSourceSettings
                        .create(connectionFactory)
                        .withQueue("Orders")
                        .withBufferSize(10)
                );
        CompletionStage<List<String>> result = jmsSource
                .take(1)
                .runWith(Sink.seq(), mat)
                .thenApply(response -> {
                    String emailToSend = response.toString().replace("[", "").replace("]", "");
                    System.out.println("mail to send:" + emailToSend);
                    mailer.send(emailToSend);
                    return Arrays.asList(response.toString());
                });
    }
}
