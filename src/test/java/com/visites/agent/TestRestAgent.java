package com.visites.agent;

import com.visites.agent.repository.AgentRepository;
import com.visites.agent.model.Agent;
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

        ir.Create("Bob","Letton", "90982818");

        Query query = emf.createQuery("SELECT max(i.id) FROM Agent i");
        int last = (int) query.getSingleResult();
        emf.close();

        int id = ir.Create("Bob", "Letton","90982818");

        org.junit.Assert.assertEquals(last + 1, id);

    }

    @Test
    public void BtestGet() {

        AgentRepository ir = new AgentRepository();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ini_PU");
        EntityManager emf = entityManagerFactory.createEntityManager();
        Query query = emf.createQuery("SELECT max(i.id) FROM Agent i");
        int last = (int) query.getSingleResult();
        emf.close();

        String yml = ir.Read(last);

        org.junit.Assert.assertEquals(yml, "{first_name:Bob, last_name:Letton, telephone:90982818}");
    }

    @Test
    public void CtestUpdate() {

        AgentRepository ir = new AgentRepository();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ini_PU");
        EntityManager emf = entityManagerFactory.createEntityManager();
        Query query = emf.createQuery("SELECT max(i.id) FROM Agent i");
        int last = (int) query.getSingleResult();
        emf.close();

        int res = ir.Update(last, "{TestUpdate: aze}", "{TestUpdate: aze}","{TestUpdate: aze}");

        org.junit.Assert.assertEquals(res, 1);

        String yml = ir.Read(last);

        org.junit.Assert.assertEquals(yml, "{TestUpdate: aze}");

    }

    @Test
    public void DtestDelete() {
        AgentRepository ir = new AgentRepository();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ini_PU");
        EntityManager emf = entityManagerFactory.createEntityManager();
        Query query = emf.createQuery("SELECT max(i.id) FROM Agent i");
        int last = (int) query.getSingleResult();
        emf.close();

        int res = ir.Delete(last);

        org.junit.Assert.assertEquals(res, 1);
    }
}
