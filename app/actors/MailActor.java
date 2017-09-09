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
import org.apache.activemq.ActiveMQConnectionFactory;
import play.mvc.*;

import java.util.*;
import java.util.concurrent.CompletionStage;

import static play.mvc.Results.ok;


public class MailActor extends AbstractActor {

    private Materializer mat;

    public MailActor (Materializer materializer) {
        this.mat = materializer;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, str -> {
                    String reply = "Hello" + str;
                    sendToQueue(str);
                    receiveFromQueue();
                    sender().tell(reply, self());
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
                    return Arrays.asList(response.toString());
                });
    }
}
