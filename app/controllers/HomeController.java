package controllers;

import actors.HelloActor;
import actors.HelloActorProtocol;
import play.mvc.*;
import akka.actor.*;
import scala.compat.java8.FutureConverters;
import javax.inject.*;
import java.util.concurrent.CompletionStage;

import static akka.pattern.Patterns.ask;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
@Singleton
public class HomeController extends Controller {

    final ActorRef helloActor;

    @Inject public HomeController(ActorSystem system) {
        helloActor = system.actorOf(HelloActor.getProps());
    }

    public CompletionStage<Result> sayHello(String name) {
        return FutureConverters.toJava(ask(helloActor, new HelloActorProtocol.SayHello(name), 1000))
                .thenApply(response -> ok("Hello, " + name));
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render());
    }

}
