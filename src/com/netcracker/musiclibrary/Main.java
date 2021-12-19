package com.netcracker.musiclibrary;

import com.netcracker.musiclibrary.controller.Controller;
import com.netcracker.musiclibrary.data.Genre;
import com.netcracker.musiclibrary.data.Track;
import com.netcracker.musiclibrary.model.Model;
import com.netcracker.musiclibrary.view.View;

import java.time.Duration;
import java.util.ArrayList;

/**
 * @author Alexander D., Anna S., Ivan L..
 * @version 1.0
 * @since 1.6
 */
public class Main {
    /**
     * Here start point of the program
     * @param args command line values
     */
    public static void main(String[] args){

        ArrayList<Track> tracks = new ArrayList<>();
        ArrayList<Genre> genres = new ArrayList<>();

        genres.add(new Genre("Рок"));
        genres.add(new Genre("Джаз"));
        genres.add(new Genre("Шансон"));
        genres.add(new Genre("Шанси"));

        Duration time = Duration.ofSeconds(200);
        for (int i = 1; i < 6; i++){
            tracks.add(new Track("Песня" + i, "Певец" + i, "Альбом" + i, time, genres.get(i % 4)));
        }

        Model model = new Model(tracks, genres);
        Controller controller = new Controller(model);
        View view = new View(model, controller);
    }
}
