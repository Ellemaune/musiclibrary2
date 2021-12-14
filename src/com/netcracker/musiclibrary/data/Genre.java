package com.netcracker.musiclibrary.data;

import java.util.Objects;

public class Genre {
    private String name;

    public Genre(String name){
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj){
        if (((Genre)obj).getName().equals(this.getName())) return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString(){
        return this.getName();
    }

}
