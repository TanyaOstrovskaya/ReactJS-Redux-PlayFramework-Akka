package controllers;

import play.mvc.*;
import javax.inject.*;
import static play.mvc.Results.ok;

@Singleton
public class MainController {

    @Inject
    public MainController() {
    }

    public Result main () {
        return ok(views.html.main.render());
    }
}
