package actors;

import akka.actor.*;
import akka.actor.AbstractActor;
import models.PointEntry;

public class PointActor extends AbstractActor {

    public static Props getProps() {
        return Props.create(PointActor.class);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(PointEntry.class, point -> {
                    PointEntry reply = new PointEntry(point.getX(), point.getY(), point.getR());
                    sender().tell(reply, self());
                })
                .build();
    }


}