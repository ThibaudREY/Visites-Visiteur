package com.visites.visiteur;

import com.visites.visiteur.repository.VisiteurRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRestVisiteur {

    @Test()
    public void AtestCreate() {

        VisiteurRepository ir = new VisiteurRepository();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ini_PU");
        EntityManager emf = entityManagerFactory.createEntityManager();

        ir.Create("Bob","Letton", "90982818");

        Query query = emf.createQuery("SELECT max(i.id) FROM Visiteur i");
        int last = (int) query.getSingleResult();
        emf.close();

        int id = ir.Create("Bob", "Letton","90982818");

        org.junit.Assert.assertEquals(last + 1, id);

    }

    @Test
    public void BtestGet() {

        VisiteurRepository ir = new VisiteurRepository();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ini_PU");
        EntityManager emf = entityManagerFactory.createEntityManager();
        Query query = emf.createQuery("SELECT max(i.id) FROM Visiteur i");
        int last = (int) query.getSingleResult();
        emf.close();

        String yml = ir.Read(last);

        org.junit.Assert.assertEquals(yml, "{first_name:Bob, last_name:Letton, telephone:90982818}");
    }

    @Test
    public void CtestUpdate() {

        VisiteurRepository ir = new VisiteurRepository();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ini_PU");
        EntityManager emf = entityManagerFactory.createEntityManager();
        Query query = emf.createQuery("SELECT max(i.id) FROM Visiteur i");
        int last = (int) query.getSingleResult();
        emf.close();

        int res = ir.Update(last, "{TestUpdate: aze}", "{TestUpdate: aze}","{TestUpdate: aze}");

        org.junit.Assert.assertEquals(res, 1);

        String yml = ir.Read(last);

        org.junit.Assert.assertEquals(yml, "{TestUpdate: aze}");

    }

    @Test
    public void DtestDelete() {
        VisiteurRepository ir = new VisiteurRepository();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ini_PU");
        EntityManager emf = entityManagerFactory.createEntityManager();
        Query query = emf.createQuery("SELECT max(i.id) FROM Visiteur i");
        int last = (int) query.getSingleResult();
        emf.close();

        int res = ir.Delete(last);

        org.junit.Assert.assertEquals(res, 1);
    }
}
