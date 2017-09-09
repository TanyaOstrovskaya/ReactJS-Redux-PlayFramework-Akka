package controllers;

import email.SendMailTLS;
import play.mvc.*;
import javax.inject.*;

@Singleton
public class MainController extends Controller {

    @Inject
    public MainController() {

    }

    public static Result signUpNewUser() {
        return Results.TODO;
    }

    public Result main () {
        return ok(views.html.main.render());
    }

    public Result send () {
        SendMailTLS.send();
        return  ok ("OK");
    }
}
