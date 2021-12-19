package com.netcracker.musiclibrary.controller;

import com.netcracker.musiclibrary.data.Genre;
import com.netcracker.musiclibrary.data.Track;
import com.netcracker.musiclibrary.model.Model;
import com.netcracker.musiclibrary.view.View;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Locale;

/**
 * The Controller class containing the Model object and methods for working with it
 */
public class Controller {
    private Model model;

    /**
     * default constructor
     * @see Model
     */
    public Controller(Model model){
        this.model = model;
    }

    /**
     * Method for adding a track to an array
     * @see Track
     */
    public boolean createTrack(String name, String singer,
            String album, Duration recordLength, String nameOfGenre){
        Genre genre = createGenre(nameOfGenre, false);
        Track track = new Track(name, singer, album, recordLength, genre);
        return model.addTrack(track, true);
    }
    /**
     * Method for adding genre to array
     * @see Genre
     */
    public Genre createGenre(String nameGenre, boolean notifyFlag)
                    throws RuntimeException {
        if (!nameGenre.equals("")) {
            Genre genre = new Genre(nameGenre);
            model.addGenre(genre, notifyFlag);
            return genre;
        }
        throw new RuntimeException("имя жанра не может быть пустым!");
    }

    /**
     * Serialization Method
     * @param fileName - Save file name
     * @throws IOException - error related to serialization process
     */
    public void saveDataToFile(String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this.model.getTracksCollection());
        objectOutputStream.writeObject(this.model.getGenresCollection());
        objectOutputStream.close();
    }

    /**
     * Method for removing a Track from an array by index
     * @param index - index in the array (input starts with one)
     */
    public void removeTrack(int index)
    {
        if(index-1>=0 && index-1<model.getTracksCollection().size()) {
            ArrayList<Track> tracks = (ArrayList<Track>) model.getTracksCollection();
            this.model.removeTrack(tracks.get(index-1));
        }else
        {
            this.model.notifyAboutChanges();
        }
    }

    /**
     * Method for removing a Genre from an array by index
     * @param index - index in the array (input starts with one)
     */
    public void removeGenre(int index)
    {
        if(index-1>=0 && index-1<=model.getGenresCollection().size()) {
            ArrayList<Genre> genres = (ArrayList<Genre>) model.getGenresCollection();
            ArrayList<Track> tracks = (ArrayList<Track>) model.getTracksCollection();

            for(int i = 0 ; i<model.getTracksCollection().size();i++)
            {
                if(tracks.get(i).genre().equals(genres.get(index-1)))
                {
                    tracks.remove(i);
                }
            }
            this.model.removeGenre(genres.get(index-1));
        }else
        {
            this.model.notifyAboutChanges();
        }
    }


    /**
     * Method for changing a Track object by index in an array
     * @param index -index in the array (input starts with one)
     * @see Track
     */
    public void editTrack(int index,String name, String singer,
                          String album, Duration recordLength, String nameOfGenre){
        Genre genre = createGenre(nameOfGenre, false);
        Track track = new Track(name, singer, album, recordLength, genre);

        if(index-1>=0 && index-1<model.getTracksCollection().size()) {
            ArrayList<Track> tracks = (ArrayList<Track>) model.getTracksCollection();
            tracks.set(index-1,track);
            this.model.setTracksCollection(tracks);
        }else
        {
            this.model.notifyAboutChanges();
        }
    }

    /**
     * Method for changing a Genre object by index in an array
     * @param index - index in the array (input starts with one)
     * @see  Genre
     */
    public void editGenre(int index, String nameOfGenre)
    {
        Genre newGenre = new Genre(nameOfGenre);
        if(index-1>=0 && index-1<=model.getGenresCollection().size() && newGenre.name()!="") {
            ArrayList<Genre> genres = (ArrayList<Genre>) model.getGenresCollection();
            Genre oldGenre = genres.get(index-1);
            genres.set(index-1,newGenre);
            ArrayList<Track> tracks = (ArrayList<Track>)model.getTracksCollection();
            for(int i =0 ; i<tracks.size();i++)
            {
                if(tracks.get(i).genre().equals(oldGenre))
                {
                    Track newTrack = new Track(tracks.get(i).name(),tracks.get(i).singer(),tracks.get(i).album(),tracks.get(i).recordLength(),newGenre);
                    tracks.set(i,newTrack);
                }
            }
            this.model.setGenresCollection(genres);
        }else
        {
            this.model.notifyAboutChanges();
        }

    }
    /**
     * Deserialization Method
     * @param fileName - upload file name
     * @throws IOException - error related to serialization process
     * @throws ClassNotFoundException - error related to loading a specific class
     */
    public void updateDataFromFile(String fileName)
                throws IOException, ClassNotFoundException {

        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ArrayList<Track> tracks = (ArrayList<Track>) objectInputStream.readObject();
        ArrayList<Genre> genres = (ArrayList<Genre>) objectInputStream.readObject();
        objectInputStream.close();
        this.model.addAllUniqueData(tracks, genres);
    }
}
