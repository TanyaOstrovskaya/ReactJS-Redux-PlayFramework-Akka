package actors;

import akka.actor.*;
import akka.actor.AbstractActor;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import models.PointEntry;
import models.SendInfo;

public class PointActor extends AbstractActor {

    public static Props getProps() {
        return Props.create(PointActor.class);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(SendInfo.class, sendinfo -> {
                    PointEntry point = new PointEntry(sendinfo.getPoint().getX(), sendinfo.getPoint().getY(), sendinfo.getPoint().getR());
                    ActorSystem system = getContext().system();
                    Materializer mat = ActorMaterializer.create(system);
                    final ActorRef mailActor  = getContext().actorOf(Props.create(MailActor.class, mat));
                    SendInfo resut = new SendInfo(sendinfo.getEmail(), point.getResult() > 0, point, sendinfo.isNew());

                    mailActor.tell(resut, getSelf());
                    sender().tell(point, self());
                })
                .build();
    }


}