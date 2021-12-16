package com.netcracker.musiclibrary.model;

import com.netcracker.musiclibrary.data.Genre;
import com.netcracker.musiclibrary.data.Track;

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

    public void setGenresCollection(Collection<Genre> genres){
        this.genres = genres;
    }

    public void setTracksCollection(Collection<Track> tracks){
        this.tracks = tracks;
    }

    public void addAllUniqueData(Collection<Track> tracks, Collection<Genre> genres){
        for (Track track: tracks){
            addTrack(track, false);
        }
        for(Genre genre: genres){
            addGenre(genre, false);
        }
        notifyAboutChanges();
    }

    public void addChangeListener(ModelChangeListener listener){
        listeners.add(listener);
    }

    public void notifyAboutChanges(){
        for (ModelChangeListener listener: listeners)
            listener.onModelChangeListener();
    }

    public Track getTrack(String nameTrack){
        for(Track track : getTracksCollection()){
            if(track.name().equals(nameTrack)){
                return track;
            }
        }
        return null;
    }

    public Genre getGenre(String nameGenre){
        for(Genre genre : getGenresCollection()) {
            if (genre.name().equals(nameGenre)){
                return genre;
            }
        }
        return null;
    }

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

    public boolean removeTrack(Track track){
        if(getTracksCollection().remove(track)){
            notifyAboutChanges();
            return true;
        }
        return false;
    }

    public boolean removeGenre(Genre genre){
        if(getGenresCollection().remove(genre)){
            notifyAboutChanges();
            return true;
        }
        return false;
    }

    public boolean isContainedInTracks(Track track){
        return getTracksCollection().contains(track);
    }

    public boolean isContainedInGenres(Genre genre){
        return getGenresCollection().contains(genre);
    }
}
