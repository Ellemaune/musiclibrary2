package com.netcracker.musiclibrary.model;

import com.netcracker.musiclibrary.data.Genre;
import com.netcracker.musiclibrary.data.Track;
import com.netcracker.musiclibrary.view.ModelChangeListener;

import java.util.ArrayList;
import java.util.Collection;

public class Model {

    private Collection<Genre> genres;
    private Collection<Track> tracks;
    private Collection<ModelChangeListener> listeners;

    public Model(){
        this.genres = new ArrayList<>();
        this.tracks = new ArrayList<>();
        this.listeners = new ArrayList<>();
    }

    public Model(ArrayList tracks,ArrayList genres){
        this.genres = genres;
        this.tracks = tracks;
        this.listeners = new ArrayList<>();
    }

    public Collection<Genre> getGenresCollection(){
        return this.genres;
    }

    public Collection<Track> getTracksCollection(){
        return this.tracks;
    }

    public void addChangeListener(ModelChangeListener listener){
        listeners.add(listener);
    }

    public Track getTrack(String nameTrack){
        for(Track track : getTracksCollection()){
            if(track.getName().equals(nameTrack)){
                return track;
            }
        }
        return null;
    }

    public Genre getGenre(String nameGenre){
        for(Genre genre : getGenresCollection()) {
            if (genre.getName().equals(nameGenre)){
                return genre;
            }
        }
        return null;
    }

    public boolean addTrack(Track track){
        if (!getTracksCollection().contains(track)){
            getTracksCollection().add(track);
            for (ModelChangeListener listener: listeners)
                listener.onModelChangeListener();
            return true;
        }
        return false;
    }

    public boolean addGenre(Genre genre){
        if(!getGenresCollection().contains(genre)){
            getGenresCollection().add(genre);
            for (ModelChangeListener listener: listeners)
                listener.onModelChangeListener();
            return true;
        }
        return false;
    }

    public boolean removeTrack(Track track){
        return getTracksCollection().remove(track);
    }

    public boolean removeGenre(Genre genre){
        return getGenresCollection().remove(genre);
    }

    public boolean isContainedInTracks(Track track){
        return getTracksCollection().contains(track);
    }

    public boolean isContainedInGenres(Genre genre){
        return getGenresCollection().contains(genre);
    }

}
