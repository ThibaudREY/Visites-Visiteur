package com.visites.agent.rest;

import com.visites.agent.repository.AgentRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class RestAgent {


    @POST
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces({MediaType.TEXT_PLAIN})
    public Response Create(String first_name, String last_name, String telephone) {

        AgentRepository ir = new AgentRepository();

        return Response.status(200).entity(ir.Create(first_name, last_name, telephone)).build();

    }


    @DELETE
    @Path("/{id}")
    public Response Delete(@PathParam("id") int id) {

        AgentRepository ir = new AgentRepository();

        ir.Delete(id);

        return Response.status(200).entity("Acknoleged: true").build();

    }

    @PUT
    @Path("/{id}")
    public Response Update(@PathParam("id") int id, String first_name, String last_name, String telephone) {

        AgentRepository ir = new AgentRepository();

        ir.Update(id, first_name, last_name, telephone);

        return Response.status(200).entity("Acknoleged: true").build();

    }

    @GET
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/{id}")
    public Response Read(@PathParam("id") int id) {

        AgentRepository ir = new AgentRepository();

        return Response.status(200).entity(ir.Read(id)).build();

    }

}
