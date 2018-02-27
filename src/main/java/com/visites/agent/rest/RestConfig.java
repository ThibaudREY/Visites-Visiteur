package com.visites.agent.rest;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@Stateless
@ApplicationPath("/agent")
public class RestConfig  extends Application {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ini_PU");
    EntityManager emf = entityManagerFactory.createEntityManager();



    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> resources = new HashSet<>();

        System.out.println(emf);

        System.out.println("********");

        System.out.println("REST configuration starting: getClasses()");

        resources.add(org.glassfish.jersey.jackson.JacksonFeature.class);


        resources.add(RestAgent.class);


        System.out.println("REST configuration ended successfully.");

        return resources;
    }











}
