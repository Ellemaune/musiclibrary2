package com.netcracker.musiclibrary.controller;

import com.netcracker.musiclibrary.data.Genre;
import com.netcracker.musiclibrary.data.Track;
import com.netcracker.musiclibrary.model.Model;
import com.netcracker.musiclibrary.view.View;

import javax.swing.*;
import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Locale;

public class Controller {
    private Model model;

    public Controller(Model model){
        this.model = model;
    }

    public boolean createTrack(String name, String singer,
            String album, Duration recordLength, String nameOfGenre){
        Genre genre = createGenre(nameOfGenre, false);
        Track track = new Track(name, singer, album, recordLength, genre);
        return model.addTrack(track, true);
    }

    public Genre createGenre(String nameGenre, boolean notifyFlag)
                    throws RuntimeException {
        if (!nameGenre.equals("")) {
            Genre genre = new Genre(nameGenre);
            model.addGenre(genre, notifyFlag);
            return genre;
        }
        throw new RuntimeException("Имя жанра не может быть пустым!");
    }

    public void searchName(String str, Model modelSearch, View view){
        for (Track track : model.getTracksCollection()){
            if (track.getName().toLowerCase(Locale.ROOT).contains(str.toLowerCase(Locale.ROOT)))
                modelSearch.addTrack(track);
        }
        for (Genre genre : model.getGenresCollection()){
            if (genre.getName().toLowerCase(Locale.ROOT).contains(str.toLowerCase(Locale.ROOT)))
                modelSearch.addGenre(genre, false);
        }
        modelSearch.addChangeListener(view);
        modelSearch.notifyAboutCreate();
    }

    public void saveDataToFile(String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this.model.getTracksCollection());
        objectOutputStream.writeObject(this.model.getGenresCollection());
        objectOutputStream.close();
        model.notifyAboutChanges();
    }

    public void inputDataFromFile(String fileName)
                throws IOException, ClassNotFoundException {

        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ArrayList<Track> tracks = (ArrayList<Track>) objectInputStream.readObject();
        ArrayList<Genre> genres = (ArrayList<Genre>) objectInputStream.readObject();
        objectInputStream.close();
        this.model.setGenresCollection(genres);
        this.model.setTracksCollection(tracks);
        model.notifyAboutChanges();
    }

    public void updateDataFromFile(String fileName)
                throws IOException, ClassNotFoundException {

        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ArrayList<Track> tracks = (ArrayList<Track>) objectInputStream.readObject();
        ArrayList<Genre> genres = (ArrayList<Genre>) objectInputStream.readObject();
        objectInputStream.close();
        for (Track track: tracks){
            this.model.addTrack(track, false);
        }
        for(Genre genre: genres){
            this.model.addGenre(genre, false);
        }
        model.notifyAboutChanges();
    }
}
