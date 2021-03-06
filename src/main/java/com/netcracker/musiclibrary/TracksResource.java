package com.netcracker.musiclibrary;

import com.netcracker.musiclibrary.controller.Controller;
import com.netcracker.musiclibrary.data.Genre;
import com.netcracker.musiclibrary.data.Track;
import com.netcracker.musiclibrary.model.Model;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Duration;
import java.util.Collection;
@Path("/tracks")

public class TracksResource {
    @Inject
    Model model;

    @Inject
    Controller controller;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Track> getTracks() {

        return model.getTracksCollection();
    }

    @DELETE
    @Path("/removeTracks/{namesTracks}")
    public Response removeTrack(@PathParam("namesTracks") String namesTracks){
        String[] nameArr = namesTracks.split(",");
        for (String nameTrack : nameArr){
            model.removeTrack(model.getTrack(nameTrack));
        }
        return Response.status(200).build();
    }
    @POST
    @Path("/addTracks/{paramTrack}")
    public Response addTrack(@PathParam("paramTrack") String paramTrack){
        String[] nameArr = paramTrack.split(",");
        model.addTrack(controller.createTrack(nameArr[0], nameArr[1], nameArr[2], Duration.ofSeconds(Long.parseLong(nameArr[3])), nameArr[4]));
        // для возможности подобной реализации в Controller был изменен возвращаемый тип у метода createTrack

        return Response.status(201).build();
    }
}
