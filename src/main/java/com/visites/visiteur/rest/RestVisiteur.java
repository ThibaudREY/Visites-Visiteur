package com.visites.visiteur.rest;

import com.visites.visiteur.repository.VisiteurRepository;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class RestVisiteur {


    @POST
    @Produces({MediaType.TEXT_PLAIN})
    public Response Create(
            @QueryParam("first_name") String first_name,
            @QueryParam("last_name") String last_name,
            @QueryParam("telephone") String telephone,
            @QueryParam("adresse") String adresse
    ) {

        VisiteurRepository ir = new VisiteurRepository();

        System.out.println(first_name);
        System.out.println(last_name);
        System.out.println(telephone);
        System.out.println(adresse);

        return Response.status(200).entity(ir.Create(first_name, last_name, telephone,adresse)).build();

    }


    @DELETE
    @Path("/{id}")
    public Response Delete(@PathParam("id") int id) {

        VisiteurRepository ir = new VisiteurRepository();

        ir.Delete(id);

        return Response.status(200).entity("Acknoleged: true").build();

    }

    @PUT
    @Path("/{id}")
    public Response Update(
            @PathParam("id") int id,
            String body
    ) {

        JSONObject post = new JSONObject(body);

        VisiteurRepository ir = new VisiteurRepository();

        ir.Update(
                id,
                post.getString("first_name"),
                post.getString("last_name"),
                post.getString("telephone"),
                post.getString("adresse")
        );

        return Response.status(200).entity("Acknoleged: true").build();

    }

    @GET
    @Path("/{id}")
    public Response Read(@PathParam("id") int id) {

        VisiteurRepository ir = new VisiteurRepository();

        return Response.status(200).entity(ir.Read(id)).build();

    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response ReadAll() {

        VisiteurRepository ir = new VisiteurRepository();

        return Response
                .status(200).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity(ir.All()).build();

    }

}
