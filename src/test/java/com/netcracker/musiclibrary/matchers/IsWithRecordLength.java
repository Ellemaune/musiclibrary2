package com.netcracker.musiclibrary.matchers;

import com.netcracker.musiclibrary.data.Track;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import java.time.Duration;

public class IsWithRecordLength extends TypeSafeMatcher<Track>  {
    private Duration recordLength;
    Duration v;
    int a = Duration.ZERO.toSecondsPart();
    public IsWithRecordLength(Duration recordLength){
        this.recordLength = recordLength;
    }

    @Override
    protected boolean matchesSafely(Track track){
        return track.recordLength().equals(this.recordLength);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("is with track's record length " + this.recordLength);
    }

    public static Matcher<Track> withRecordLength(Duration recordLength){
        return new IsWithRecordLength(recordLength);
    }
}