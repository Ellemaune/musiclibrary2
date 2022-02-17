package com.netcracker.musiclibrary.matchers;

import com.netcracker.musiclibrary.data.Track;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class IsWithAlbum  extends TypeSafeMatcher<Track>  {
    private String album;

    public IsWithAlbum(String album){
        this.album = album;
    }

    @Override
    protected boolean matchesSafely(Track track){
        return track.album().equals(this.album);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("is with track's album " + this.album);
    }

    public static Matcher<Track> withAlbum(String album){
        return new IsWithAlbum(album);
    }
}