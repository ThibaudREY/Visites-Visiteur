package com.visites.agent;

import com.visites.agent.repository.AgentRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRestAgent {

    @Test()
    public void AtestCreate() {

        AgentRepository ir = new AgentRepository();
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

        AgentRepository ir = new AgentRepository();
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

        AgentRepository ir = new AgentRepository();
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
        AgentRepository ir = new AgentRepository();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ini_PU");
        EntityManager emf = entityManagerFactory.createEntityManager();
        Query query = emf.createQuery("SELECT max(i.id) FROM Json i");
        int last = (int) query.getSingleResult();
        emf.close();

        int res = ir.Delete(last);

        org.junit.Assert.assertEquals(res, 1);
    }
}
