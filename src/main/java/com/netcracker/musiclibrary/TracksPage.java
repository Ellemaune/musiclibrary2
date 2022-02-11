package com.netcracker.musiclibrary;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
@Path("tracks")
@ApplicationScoped
public class TracksPage {

    @Inject
    Template tracks;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return tracks.data(tracks);
    }
}
