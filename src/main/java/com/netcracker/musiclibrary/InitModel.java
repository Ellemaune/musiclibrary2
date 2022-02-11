package com.netcracker.musiclibrary;

import com.netcracker.musiclibrary.data.Genre;
import com.netcracker.musiclibrary.data.Track;
import com.netcracker.musiclibrary.model.Model;
import io.quarkus.runtime.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Duration;
import java.util.ArrayList;

@Startup
@ApplicationScoped
public class InitModel {

    private Model model;

    @Inject
    public InitModel(Model model){
        ArrayList<Track> tracks = new ArrayList<>();
        ArrayList<Genre> genres = new ArrayList<>();

        genres.add(new Genre("Рок"));
        genres.add(new Genre("Джаз"));
        genres.add(new Genre("Шансон"));
        genres.add(new Genre("Шанси"));

        Duration time = Duration.ofSeconds(200);
        for (int i = 4; i < 9; i++){
            tracks.add(new Track("Песня" + i, "Певец" + i, "Альбом" + i, time, genres.get(i % 4)));
        }
        this.model = model;
        this.model.setTracksCollection(tracks);
        this.model.setGenresCollection(genres);
    }
}
