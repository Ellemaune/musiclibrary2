package com.netcracker.musiclibrary.matchers;

import com.netcracker.musiclibrary.data.Track;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class IsWithNameTrack extends TypeSafeMatcher<Track>  {
    private String name;

    public IsWithNameTrack(String name){
        this.name = name;
    }

    @Override
    protected boolean matchesSafely(Track track){
        return track.name().equals(this.name);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("is with track's name " + this.name);
    }

    public static Matcher<Track> withNameTrack(String name){
        return new IsWithNameTrack(name);
    }
}
