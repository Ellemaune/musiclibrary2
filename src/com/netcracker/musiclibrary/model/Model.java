package com.netcracker.musiclibrary.model;

import com.netcracker.musiclibrary.data.Genre;
import com.netcracker.musiclibrary.data.Track;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Model class - a class containing references to arrays of Track and Genre objects and methods of working with them
 */
public class Model {
    /**
     * @see Genre,Track,ModelChangeListener
     */
    private Collection<Genre> genres;
    private Collection<Track> tracks;
    private Collection<ModelChangeListener> listeners;

    /**
     * Default constructor
     */
    public Model(){
        this.genres = new ArrayList<>();
        this.tracks = new ArrayList<>();
        this.listeners = new ArrayList<>();
    }

    /**
     * Constructor with parameters of Tracks and Genres arrays
     * @param tracks - Array of Track
     * @param genres - Array of Genre
     */
    public Model(ArrayList tracks,ArrayList genres){
        this.genres = genres;
        this.tracks = tracks;
        this.listeners = new ArrayList<>();
    }

    /**
     * @return Collection of Genre
     */
    public Collection<Genre> getGenresCollection(){
        return this.genres;
    }

    /**
     * @return Collection of Track
     */
    public Collection<Track> getTracksCollection(){
        return this.tracks;
    }

    /**
     * @param genres Collection of Genre
     */
    public void setGenresCollection(Collection<Genre> genres){
        this.genres = genres;
        notifyAboutChanges();
    }
    /**
     * @param tracks Collection of Track
     */
    public void setTracksCollection(Collection<Track> tracks){
        this.tracks = tracks;
        notifyAboutChanges();
    }

    /**
     * Method for adding unique Tracks or Genres
     * @param tracks - Array of Track
     * @param genres - Array of Genre
     */
    public void addAllUniqueData(Collection<Track> tracks, Collection<Genre> genres){
        for (Track track: tracks){
            addTrack(track, false);
        }
        for(Genre genre: genres){
            addGenre(genre, false);
        }
        notifyAboutChanges();
    }

    /**
     * Method for adding change a listener
     * @param listener - слушатель
     */
    public void addChangeListener(ModelChangeListener listener){
        listeners.add(listener);
    }

    /**
     * Change notification method
     */
    public void notifyAboutChanges(){
        for (ModelChangeListener listener: listeners)
            listener.onModelChangeListener();
    }

    /**
     * @param nameTrack - name of Track
     */
    public Track getTrack(String nameTrack){
        for(Track track : getTracksCollection()){
            if(track.name().equals(nameTrack)){
                return track;
            }
        }
        return null;
    }

    /**
     * @param nameGenre - name of Genre
     */
    public Genre getGenre(String nameGenre){
        for(Genre genre : getGenresCollection()) {
            if (genre.name().equals(nameGenre)){
                return genre;
            }
        }
        return null;
    }

    /**
     * Method for adding a unique track
     * @see Track
     * @param notifyFlag - uniqueness notification flag
     * @return
     */
    public boolean addTrack(Track track, boolean notifyFlag){
        if (!isContainedInTracks(track)){
            getTracksCollection().add(track);
            if(notifyFlag) {
                notifyAboutChanges();
            }
            return true;
        }
        return false;
    }
    /**
     * Method for adding a unique genre
     * @see Genre
     * @param notifyFlag - uniqueness notification flag
     * @return
     */
    public boolean addGenre(Genre genre, boolean notifyFlag){
        if(!isContainedInGenres(genre)){
            getGenresCollection().add(genre);
            if(notifyFlag) {
                notifyAboutChanges();
            }
            return true;
        }
        return false;
    }

    /**
     * Track Removal Method
     * @see Track
     * @return - deletion notification
     */
    public boolean removeTrack(Track track){
        if(getTracksCollection().remove(track)){
            notifyAboutChanges();
            return true;
        }
        notifyAboutChanges();
        return false;
    }

    /**
     * Genre Removal Method
     * @see Genre
     * @return - deletion notification
     */
    public boolean removeGenre(Genre genre){
        if(getGenresCollection().remove(genre)){
            notifyAboutChanges();
            return true;
        }
        notifyAboutChanges();
        return false;
    }

    /**
     * Method to find duplicate Tracks in a collection
     * @see Track
     * @return an Iterator over the elements in this collection
     */
    public boolean isContainedInTracks(Track track){
        return getTracksCollection().contains(track);
    }

    /**
     *  Method to find duplicate Genre in a collection
     * @see Genre
     * @return an Iterator over the elements in this collection
     */
    public boolean isContainedInGenres(Genre genre){
        return getGenresCollection().contains(genre);
    }
}
