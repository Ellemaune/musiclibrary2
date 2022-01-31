package com.netcracker.musiclibrary;

import com.netcracker.musiclibrary.controller.Controller;
import com.netcracker.musiclibrary.data.Genre;
import com.netcracker.musiclibrary.data.Track;
import com.netcracker.musiclibrary.model.Model;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("/file")
public class FileResource {
    @Inject
    Model model;

    @Context
    private ServletContext context;

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(MultipartFormDataInput input) {
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("attachment");
        for (InputPart inputPart : inputParts) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(inputPart.getBody(InputStream.class, null));
                ArrayList<Track> tracks = (ArrayList<Track>) objectInputStream.readObject();
                ArrayList<Genre> genres = (ArrayList<Genre>) objectInputStream.readObject();
                objectInputStream.close();
                this.model.addAllUniqueData(tracks, genres);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @GET
    @Path("/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFileWithGet() {
        StreamingOutput output = outputStream -> {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(this.model.getTracksCollection());
            objectOutputStream.writeObject(this.model.getGenresCollection());
            objectOutputStream.close();
            outputStream.flush();
        };
        Response.ResponseBuilder response = Response.ok(output);
        response.header("Content-Disposition", "file");
        response.encoding("UTF-8");
        return response.build();
    }
}
