package com.visites.visiteur.repository;

import com.visites.visiteur.model.Visiteur;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.List;

public class VisiteurRepository {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ini_PU");


    public int Create(String first_name, String last_name, String telephone) {

        EntityManager emf = entityManagerFactory.createEntityManager();
        Session session = (Session) emf.getDelegate();

        session.getTransaction().begin();

        Visiteur visiteur = new Visiteur();
        visiteur.setFirstName(first_name);
        visiteur.setLastName(last_name);
        visiteur.setTelephone(telephone);

        int id = (Integer) session.save(visiteur);

        session.getTransaction().commit();

        emf.close();

        return id;
    }

    public String Read(int id) {

        EntityManager emf = entityManagerFactory.createEntityManager();

        Query query = emf.createQuery("SELECT i FROM Visiteur i WHERE i.id = :id");

        query.setParameter("id", id);

        Visiteur res = (Visiteur) query.getSingleResult();

        emf.close();

        return res.toString();
    }

    public int Update(int id, String first_name, String last_name, String telephone) {

        EntityManager emf = entityManagerFactory.createEntityManager();
        Session session = (Session) emf.getDelegate();

        session.getTransaction().begin();

        Query query = emf.createQuery("UPDATE Visiteur i SET i.first_name = :first_name, i.last_name = :last_name, i.telephone = := telephone WHERE i.id = :id");

        query.setParameter("first_name", first_name);
        query.setParameter("last_name", last_name);
        query.setParameter("telephone", telephone);
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

        Query query = emf.createQuery("DELETE FROM Visiteur i WHERE i.id = :id");
        query.setParameter("id", id);

        int res = query.executeUpdate();

        session.getTransaction().commit();

        emf.close();

        return res;
    }

    public List All() {
        EntityManager emf = entityManagerFactory.createEntityManager();
        Session session = (Session) emf.getDelegate();

        session.getTransaction().begin();

        Query query = emf.createQuery("FROM Visiteur i");

        List res = query.getResultList();

        session.getTransaction().commit();

        emf.close();

        return res;
    }
}
