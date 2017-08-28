package controllers;
import actors.PointActor;
import actors.PointActorProtocol;
import play.mvc.*;
import akka.actor.*;
import scala.compat.java8.FutureConverters;
import javax.inject.*;
import java.util.concurrent.CompletionStage;

import static akka.pattern.Patterns.ask;

@Singleton
public class HomeController extends Controller {

    final ActorRef mainActor;

    @Inject public HomeController(ActorSystem system) {
        mainActor = system.actorOf(PointActor.getProps());
    }


    public CompletionStage<Result> sayHello (double x, double y, double r) {
        return FutureConverters.toJava(ask(mainActor, new PointActorProtocol.Point(x, y, r), 1000))
                .thenApply(response -> {
                    PointActorProtocol.Point point = (PointActorProtocol.Point)response;
                    return ok(point.toString());
                });
    }

    public Result index() {
        return ok(views.html.index.render());
    }

}
