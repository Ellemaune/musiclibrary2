package com.netcracker.musiclibrary;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

@Path("file")
@ApplicationScoped
public class GenresPage {

    @Inject
    Template genres;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get()
    {
        return genres.data(genres);
    }
}
