package com.data.json.repository;

import com.data.json.model.Json;
import org.hibernate.Session;

import javax.persistence.*;

public class JsonRepository {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ini_PU");


    public int Create(String body) {

        EntityManager emf = entityManagerFactory.createEntityManager();
        Session session = (Session) emf.getDelegate();

        session.getTransaction().begin();

        Json json = new Json();
        json.setBody(body);

        int id = (Integer) session.save(json);

        session.getTransaction().commit();

        emf.close();

        return id;
    }

    public String Read(int id) {

        EntityManager emf = entityManagerFactory.createEntityManager();

        Query query = emf.createQuery("SELECT i FROM Json i WHERE i.id = :id");

        query.setParameter("id", id);

        Json res = (Json) query.getSingleResult();

        emf.close();

        return res.getBody();
    }

    public int Update(int id, String body) {

        EntityManager emf = entityManagerFactory.createEntityManager();
        Session session = (Session) emf.getDelegate();

        session.getTransaction().begin();

        Query query = emf.createQuery("UPDATE Json i SET i.body = :body WHERE i.id = :id");

        query.setParameter("body", body);
        query.setParameter("id", id);

        int res = query.executeUpdate();

        session.getTransaction().commit();

        emf.close();

        return res;
    }

    public int Delete(int id) {

        EntityManager emf = entityManagerFactory.createEntityManager();
        Session session = (Session) emf.getDelegate();

        session.getTransaction().begin();

        Query query = emf.createQuery("DELETE FROM Json i WHERE i.id = :id");
        query.setParameter("id", id);

        int res = query.executeUpdate();

        session.getTransaction().commit();

        emf.close();

        return res;
    }

}
