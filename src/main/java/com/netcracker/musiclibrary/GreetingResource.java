package com.netcracker.musiclibrary;

import com.netcracker.musiclibrary.data.Genre;
import com.netcracker.musiclibrary.data.Track;
import com.netcracker.musiclibrary.model.Model;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/")
public class GreetingResource {
    @Inject
    Model model;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String myprog() {
        return "go to tracks or genres";
    }

    @GET
    @Path("/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Track> getTracks() {
        return model.getTracksCollection();
    }

    @GET
    @Path("/genres")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Genre> getGenres() {
        return model.getGenresCollection();
    }
}