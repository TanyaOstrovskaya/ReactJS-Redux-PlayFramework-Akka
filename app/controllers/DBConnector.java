package controllers;

import models.PointEntry;
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
        List<PointEntry> res = getNamesFromDB();
        return ok (res.get(0).toString());
    }

    @Transactional
    public List<PointEntry> getNamesFromDB () {
        List<PointEntry> res = jpaApi.withTransaction(entityManager -> {
            Query query = entityManager.createQuery("select p from PointEntry p", PointEntry.class);
            return (List<PointEntry>) query.getResultList();
        });
        return res;
    }
}
