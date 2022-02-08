package com.netcracker.musiclibrary;

import com.netcracker.musiclibrary.controller.Controller;
import com.netcracker.musiclibrary.data.Genre;
import com.netcracker.musiclibrary.model.Model;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/genres")
public class GenresResource {
    @Inject
    Model model;

    @Inject
    Controller controller;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Genre> getGenres() {

        return model.getGenresCollection();
    }

    @DELETE
    @Path("/removeGenres/{namesGenres}")
    public Response removeGenre(@PathParam("namesGenres") String namesGenres){
        String[] nameArr = namesGenres.split(",");
        for (String nameGenre : nameArr){
            model.removeGenre(model.getGenre(nameGenre));
        }
        return Response.status(200).build();
    }

    @POST
    @Path("/addGenres/{nameGenre}")
    public Response addGenre(@PathParam("nameGenre") String nameGenre){
        model.addGenre(controller.createGenre(nameGenre));
        return Response.status(200).build();
    }
}
