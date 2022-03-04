package com.netcracker.musiclibrary;

import com.netcracker.musiclibrary.data.Genre;
import com.netcracker.musiclibrary.data.Track;
import com.netcracker.musiclibrary.model.Model;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.time.Duration;
import java.util.ArrayList;

import static com.netcracker.musiclibrary.matchers.IsWithAlbum.withAlbum;
import static com.netcracker.musiclibrary.matchers.IsWithGenre.withGenre;
import static com.netcracker.musiclibrary.matchers.IsWithName.withName;
import static com.netcracker.musiclibrary.matchers.IsWithNameTrack.withNameTrack;
import static com.netcracker.musiclibrary.matchers.IsWithRecordLength.withRecordLength;
import static com.netcracker.musiclibrary.matchers.IsWithSinger.withSinger;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItems;

@QuarkusTest
public class FileResourceTest {
    @Inject
    Model model;

    @BeforeEach
    public void cleanModel(){

        new InitModel(this.model);
    }

    @Test
    public void fileDownloadTest() throws IOException, ClassNotFoundException {
        RestAssured.given()
                .when().post("/genres/addGenre/Хой");
        RestAssured.given()
                .when().post("/tracks/addTracks/Песня13,Певец13,Альбом13,200,Хоййй")
                .then().statusCode(201);

        InputStream inputStream = given()
          .when()
                .get("/file/download").then().extract().asInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ArrayList<Track> tracks = (ArrayList<Track>) objectInputStream.readObject();
        ArrayList<Genre> genres = (ArrayList<Genre>) objectInputStream.readObject();
        objectInputStream.close();

        assertThat(genres, hasItems(withName("Хой")));
        assertThat(tracks,allOf(
                hasItems(withNameTrack("Песня13")),
                hasItems(withSinger("Певец13")),
                hasItems(withAlbum("Альбом13")),
                hasItems(withRecordLength(Duration.ofSeconds(200))),
                hasItems(withGenre("Хоййй"))
        ));
    }
}
