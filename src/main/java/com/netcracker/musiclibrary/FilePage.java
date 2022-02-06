package com.netcracker.musiclibrary;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("file")
@ApplicationScoped
public class FilePage {
    @Inject
    Template file;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get(){
        return file.data(file);
    }
}
