package com.netcracker.musiclibrary.controller;

import com.netcracker.musiclibrary.data.Genre;
import com.netcracker.musiclibrary.data.Track;
import com.netcracker.musiclibrary.model.Model;

import javax.swing.*;
import java.time.Duration;

public class Controller {
    private Model model;

    public Controller(){
        model = new Model();
    }

    public boolean createTrack(String name, String singer,
            String album, Duration recordLength, String nameOfGenre){
        Genre genre = createGenre(nameOfGenre);
        Track track = new Track(name, singer,album, recordLength,genre);
        return model.addTrack(track);
    }

    public Genre createGenre(String nameGenre) {
        if (!nameGenre.equals("")) {
            Genre genre = new Genre(nameGenre);
            if (model.isContainedInGenres(genre)) {
                model.addGenre(genre);
                return genre;
            }
        }
        return null;
    }

}
