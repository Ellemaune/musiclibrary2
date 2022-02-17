package com.netcracker.musiclibrary.matchers;

import com.netcracker.musiclibrary.data.Track;
import com.netcracker.musiclibrary.data.Genre;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class IsWithGenre extends TypeSafeMatcher<Track>  {
    private String genre;

    public IsWithGenre(String genre){
        this.genre = genre;
    }

    @Override
    protected boolean matchesSafely(Track track){
        return track.genre().name().equals(this.genre);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("is with track's genre " + this.genre);
    }

    public static Matcher<Track> withGenre(String genre){
        return new IsWithGenre(genre);
    }
}
