package controllers;

import email.SendMailTLS;
import play.mvc.*;
import javax.inject.*;

@Singleton
public class MainController extends Controller {

    @Inject
    public MainController() { }

    public static Result signUpNewUser() {
        return Results.TODO;
    }

    public Result main (String user) {
        if (user == null) {
            return badRequest("User is not signed in");
        } else {
            return ok(views.html.main.render());
        }
    }
}
