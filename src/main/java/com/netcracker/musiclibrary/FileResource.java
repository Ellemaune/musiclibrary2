package com.netcracker.musiclibrary;

import com.netcracker.musiclibrary.data.Genre;
import com.netcracker.musiclibrary.data.Track;
import com.netcracker.musiclibrary.model.Model;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    public void uploadFile(MultipartFormDataInput input) {
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
        response.header("Content-Disposition", "file.txt");
        response.encoding("UTF-8");
        return response.status(200).build();
    }
}
