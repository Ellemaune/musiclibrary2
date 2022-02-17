package com.netcracker.musiclibrary.matchers;

import com.netcracker.musiclibrary.data.Track;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class IsWithSinger extends TypeSafeMatcher<Track>  {
    private String singer;

    public IsWithSinger(String singer){
        this.singer = singer;
    }

    @Override
    protected boolean matchesSafely(Track track){
        return track.singer().equals(this.singer);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("is with track's singer " + this.singer);
    }

    public static Matcher<Track> withSinger(String singer){
        return new IsWithSinger(singer);
    }
}
