package controllers;
import actors.MailActor;
import actors.PointActor;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import com.google.gson.*;
import database.Password;
import models.PointEntry;
import models.SendInfo;
import models.UserEntry;
import play.db.Database;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.*;
import akka.actor.*;
import scala.compat.java8.FutureConverters;
import javax.inject.*;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.concurrent.CompletionStage;
import play.data.DynamicForm;
import play.data.FormFactory;


import static akka.pattern.Patterns.ask;

@Singleton
public class HomeController extends Controller {

    final ActorRef pointActor;
    final ActorRef mailActor;
    final Materializer mat;
    private Database db;
    private JPAApi jpaApi;
    private String user;
    private String email;

    @Inject FormFactory formFactory;

    @Inject
    public HomeController(ActorSystem system, Database db, JPAApi api) {
        this.mat = ActorMaterializer.create(system);
        this.pointActor = system.actorOf(PointActor.getProps());
        this.mailActor = system.actorOf(Props.create(MailActor.class, mat));
        this.db = db;
        this.jpaApi = api;
        this.user = null;
        this.email = null;
    }

    public Result changeRadius(Double r) {
        boolean res = jpaApi.withTransaction(entityManager -> {
            List<PointEntry> entries =
                    entityManager.createQuery("FROM PointEntry", PointEntry.class).getResultList();
            for (PointEntry p : entries) {
                if (PointEntry.isInArea(p.getX(), p.getY(), r ))
                    p.setResult(1);
                else
                    p.setResult(0);
            }
            return true;
        });
        return (ok(r.toString()));
    }

    @Transactional
    public CompletionStage<Result> checkPoint (double x, double y, double r, int isnew) {
        PointEntry point = new  PointEntry(x, y, r);
        return FutureConverters.toJava(ask(pointActor, new SendInfo(this.email, point.getResult() > 0 , point, isnew > 0), 1000))
                .thenApply(response -> {
                    PointEntry pointResp = (PointEntry)response;
                    if (isnew > 0) {
                        addNewPoint(pointResp);
                    }
                    System.out.println(pointResp.toString());
                    return ok(Integer.toString(pointResp.getResult()));
                });
    }

    private String getUserEmail(String user) {
        String email = null;
        try {
            UserEntry res = jpaApi.withTransaction(entityManager -> {
                TypedQuery<UserEntry> query = entityManager.createQuery("SELECT u FROM UserEntry AS u WHERE u.login = :login", UserEntry.class);
                return query.setParameter("login", user).getSingleResult();
            });
            email = res.getEmail();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return email;
    }

    public boolean addNewPoint (PointEntry point) {
        boolean res = jpaApi.withTransaction(entityManager -> {
            entityManager.persist(point);
            return true;
        });
        return res;
    }

    public boolean addNewUser (UserEntry user) {
        boolean res = jpaApi.withTransaction(entityManager -> {
            entityManager.persist(user);
            return true;
        });
        return res;
    }

    public boolean checkUserPasswd (String login, String passwd) {
        boolean result = false;
        try {
            UserEntry res = jpaApi.withTransaction(entityManager -> {
                TypedQuery<UserEntry> query = entityManager.createQuery("SELECT u FROM UserEntry AS u WHERE u.login = :login", UserEntry.class);
                return query.setParameter("login", login).getSingleResult();
            });
            if (Password.check(passwd, res.getPassword())) {
                result = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            result = false;
        }
        return result;
    }

    public Result signUpNewUser () {
        try {
            DynamicForm dynamicForm = formFactory.form().bindFromRequest();
            this.addNewUser(new UserEntry(dynamicForm.get("username"), Password.getSaltedHash(dynamicForm.get("password")), dynamicForm.get("email")));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return redirect("/");
        }
    }

    public Result signInUser() {
        String url = null;
        try {
            DynamicForm dynamicForm = formFactory.form().bindFromRequest();
            if (this.checkUserPasswd(dynamicForm.get("username"), dynamicForm.get("password"))) {
                this.user = dynamicForm.get("username");
                this.email = getUserEmail(this.user);
                url = "/main?user=" + this.user;
            } else {
                url = "/";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            url = "/";
        } finally {
            return redirect(url);
        }
    }

    public Result sendAllPoints  () {
        try {
            List<PointEntry> res = jpaApi.withTransaction(entityManager -> {
                TypedQuery<PointEntry> query = entityManager.createQuery("SELECT p FROM PointEntry p", PointEntry.class);
                return query.getResultList();
            });
            Gson gson = new GsonBuilder().create();
            JsonArray array = gson.toJsonTree(res).getAsJsonArray();
            System.out.println(array.toString());
            return ok(array.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return badRequest();
    }

    public Result index() {
        return ok(views.html.index.render());
    }

}
