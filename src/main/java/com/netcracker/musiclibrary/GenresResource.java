package com.netcracker.musiclibrary;

import com.netcracker.musiclibrary.data.Genre;
import com.netcracker.musiclibrary.model.Model;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/genres")
public class GenresResource {
    @Inject
    Model model;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Genre> getGenres() {

        return model.getGenresCollection();
    }

    /*@GET

    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        Response.ResponseBuilder response = Response.ok(model.getGenresCollection());
        response.encoding("UTF-8");
        response.type(MediaType.APPLICATION_JSON);
        return response.build();
    }*/
}
