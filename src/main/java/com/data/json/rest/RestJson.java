package com.data.json.rest;

import com.data.json.repository.JsonRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class RestJson {


    @POST
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces({MediaType.TEXT_PLAIN})
    public Response Create(String body) {

        JsonRepository ir = new JsonRepository();

        return Response.status(200).entity(ir.Create(body)).build();

    }


    @DELETE
    @Path("/{id}")
    public Response Delete(@PathParam("id") int id) {

        JsonRepository ir = new JsonRepository();

        ir.Delete(id);

        return Response.status(200).entity("Acknoleged: true").build();

    }

    @PUT
    @Path("/{id}")
    public Response Update(@PathParam("id") int id, String body) {

        JsonRepository ir = new JsonRepository();

        ir.Update(id, body);

        return Response.status(200).entity("Acknoleged: true").build();

    }

    @GET
    @Path("/{id}")
    public Response Read(@PathParam("id") int id) {

        JsonRepository ir = new JsonRepository();

        return Response.status(200).entity(ir.Read(id)).build();

    }

}
