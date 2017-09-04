package controllers;

import play.api.Play;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import play.db.*;
import play.db.jpa.JPAApi;
import play.mvc.Result;

import java.util.List;

@Singleton
public class DBConnector extends Controller {
    private Database db;
    private JPAApi jpaApi;

    @Inject
    public DBConnector (Database db, JPAApi api) {
        this.db = db;
        this.jpaApi = api;
    }

    public Result list () {
        String res = getNamesFromDB();
        System.out.println(res);
        return ok (res);
    }

    @Transactional
    public String getNamesFromDB () {
        String res = jpaApi.withTransaction(entityManager -> {
            Query query = entityManager.createNativeQuery("select count(*) from SYSTEM.PERSON");
            return (String) query.getSingleResult().toString();
        });
        return res;
    }
}
