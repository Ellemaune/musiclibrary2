package com.netcracker.musiclibrary.matchers;

import com.netcracker.musiclibrary.data.Genre;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class IsWithName extends TypeSafeMatcher<Genre> {

    private String name;

    public IsWithName(String name){
        this.name = name;
    }

    @Override
    protected boolean matchesSafely(Genre genre){
        return genre.name().equals(this.name);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("is with name " + this.name);
    }

    public static Matcher<Genre> withName(String name){
        return new IsWithName(name);
    }
}
