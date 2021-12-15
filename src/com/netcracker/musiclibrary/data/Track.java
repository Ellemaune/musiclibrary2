package com.netcracker.musiclibrary.data;

import com.netcracker.musiclibrary.data.Genre;

import java.io.Serializable;
import java.time.Duration;
import java.util.Objects;

public class Track implements Serializable {

    private String name;
    private String singer;
    private String album;
    private Duration recordLength;
    private Genre genre;

    public Track(String name, String singer, String album,
                 Duration recordLength, Genre genre){

        setName(name);
        setSinger(singer);
        setAlbum(album);
        setRecordLength(recordLength);
        setGenre(genre);
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Duration getRecordLength() {
        return recordLength;
    }

    public void setRecordLength(Duration recordLength) {

        this.recordLength = recordLength;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object obj){
        if(!((Track) obj).getAlbum().equals(this.getAlbum())) return false;
        if(!((Track) obj).getName().equals(this.getName())) return false;
        if(!((Track) obj).getSinger().equals(this.getSinger())) return false;
        if(!((Track) obj).getRecordLength().equals(this.getRecordLength())) return false;
        if(!((Track) obj).getGenre().equals(this.getGenre())) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, singer, album, recordLength, genre);
    }
}
