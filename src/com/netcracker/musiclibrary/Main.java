package com.netcracker.musiclibrary;

import com.netcracker.musiclibrary.controller.Controller;
import com.netcracker.musiclibrary.data.Genre;
import com.netcracker.musiclibrary.data.Track;
import com.netcracker.musiclibrary.model.Model;
import com.netcracker.musiclibrary.view.View;

import java.time.Duration;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        ArrayList<Track> tracks = new ArrayList<>();
        ArrayList<Genre> genres = new ArrayList<>();
        Duration time = Duration.ofSeconds(200);
        for (int i = 1; i < 6; i++){
            tracks.add(new Track("Песня" + i, "Певец" + i, "Альбом" + i, time));
        }
        genres.add(new Genre("Рок"));
        genres.add(new Genre("Джаз"));
        genres.add(new Genre("Шансон"));
        genres.add(new Genre("Шанси"));

        Model model = new Model(tracks, genres);
        Controller controller = new Controller(model);
        View view = new View(model, controller);
    }
}
