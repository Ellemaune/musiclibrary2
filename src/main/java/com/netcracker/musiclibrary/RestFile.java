package com.netcracker.musiclibrary;

import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;

import static io.smallrye.config.ConfigLogging.log;

@Path("/file")
public class RestFile {
    @GET
    @Path("/f/{fileName}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Uni<Response> getFile(@PathParam String fileName) {
        File nf = new File(fileName);
        log.info("file:" + nf.exists());
        Response.ResponseBuilder response = Response.ok((Object) nf);
        response.header("Content-Disposition", "attachment;filename=" + nf);
        Uni<Response> re = Uni.createFrom().item(response.build());
        return re;
    }
}
