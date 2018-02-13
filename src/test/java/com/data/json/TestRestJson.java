package com.data.json;

import com.data.json.repository.JsonRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRestJson {

    @Test()
    public void AtestCreate() {

        JsonRepository ir = new JsonRepository();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ini_PU");
        EntityManager emf = entityManagerFactory.createEntityManager();

        ir.Create("");

        Query query = emf.createQuery("SELECT max(i.id) FROM Json i");
        int last = (int) query.getSingleResult();
        emf.close();

        int id = ir.Create("{TestCreate: aze}");

        org.junit.Assert.assertEquals(last + 1, id);

    }

    @Test
    public void BtestGet() {

        JsonRepository ir = new JsonRepository();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ini_PU");
        EntityManager emf = entityManagerFactory.createEntityManager();
        Query query = emf.createQuery("SELECT max(i.id) FROM Json i");
        int last = (int) query.getSingleResult();
        emf.close();

        String yml = ir.Read(last);

        org.junit.Assert.assertEquals(yml, "{TestCreate: aze}");
    }

    @Test
    public void CtestUpdate() {

        JsonRepository ir = new JsonRepository();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ini_PU");
        EntityManager emf = entityManagerFactory.createEntityManager();
        Query query = emf.createQuery("SELECT max(i.id) FROM Json i");
        int last = (int) query.getSingleResult();
        emf.close();

        int res = ir.Update(last, "{TestUpdate: aze}");

        org.junit.Assert.assertEquals(res, 1);

        String yml = ir.Read(last);

        org.junit.Assert.assertEquals(yml, "{TestUpdate: aze}");

    }

    @Test
    public void DtestDelete() {
        JsonRepository ir = new JsonRepository();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ini_PU");
        EntityManager emf = entityManagerFactory.createEntityManager();
        Query query = emf.createQuery("SELECT max(i.id) FROM Json i");
        int last = (int) query.getSingleResult();
        emf.close();

        int res = ir.Delete(last);

        org.junit.Assert.assertEquals(res, 1);
    }
}
