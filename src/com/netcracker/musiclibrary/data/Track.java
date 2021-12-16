package com.netcracker.musiclibrary.data;

import com.netcracker.musiclibrary.data.Genre;

import java.io.Serializable;
import java.time.Duration;
import java.util.Objects;

public record Track (String name, String singer, String album, Duration recordLength, Genre genre) implements Serializable {

}
