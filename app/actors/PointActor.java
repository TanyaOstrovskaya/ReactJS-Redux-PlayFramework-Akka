package actors;

import akka.actor.*;
import actors.PointActorProtocol.*;

public class PointActor extends AbstractActor {

    public static Props getProps() {
        return Props.create(PointActor.class);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Point.class, point -> {
                    Point reply = new Point(point.x, point.y, point.r);
                    sender().tell(reply, self());
                })
                .build();
    }


}