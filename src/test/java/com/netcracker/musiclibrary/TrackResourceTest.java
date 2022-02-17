package com.netcracker.musiclibrary;


import com.netcracker.musiclibrary.data.Track;
import com.netcracker.musiclibrary.model.Model;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.time.Duration;
import java.util.List;


import static com.netcracker.musiclibrary.matchers.IsWithNameTrack.withNameTrack;
import static com.netcracker.musiclibrary.matchers.IsWithGenre.withGenre;
import static com.netcracker.musiclibrary.matchers.IsWithSinger.withSinger;
import static com.netcracker.musiclibrary.matchers.IsWithAlbum.withAlbum;
import static com.netcracker.musiclibrary.matchers.IsWithRecordLength.withRecordLength;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class TrackResourceTest {
    @Inject
    Model model;

    @BeforeEach
    public void cleanModel(){

        new InitModel(this.model);
    }

    @Test
    public void getTracksTest() {
        List<Track> tracksList = RestAssured.given()
                .contentType(ContentType.JSON)
                .when().get("/tracks")
                .then().statusCode(200)
                .extract().jsonPath().getList("", Track.class);

        assertThat(tracksList,allOf(
                hasItems(withNameTrack("Песня4")),
                hasItems(withSinger("Певец4")),
                hasItems(withAlbum("Альбом4")),
                hasItems(withRecordLength(Duration.ofSeconds(400))),
                hasItems(withGenre("Рок"))
        ));
    }
    @Test
    public void addTracksTest() {
        RestAssured.given()
                .when().post("/tracks/addTracks/Песня12,Певец12,Альбом12,200,Роккккк")
                .then().statusCode(201);

        assertThat(model.getTracksCollection(),allOf(
                hasItems(withNameTrack("Песня12")),
                hasItems(withSinger("Певец12")),
                hasItems(withAlbum("Альбом12")),
                hasItems(withRecordLength(Duration.ofSeconds(200))),
                hasItems(withGenre("Роккккк"))
        ));
    }

    @Test
    public void deleteTracksTest() {
        RestAssured.given()
                .when().delete("/tracks/removeTracks/Песня5")
                .then().statusCode(200);

        assertThat(model.getTracksCollection(),allOf(
                not(hasItems(withNameTrack("Песня5"))),
                not(hasItems(withSinger("Певец5"))),
                not(hasItems(withAlbum("Альбом5"))),
                not(hasItems(withRecordLength(Duration.ofSeconds(500)))),
                not(hasItems(withGenre("Джаз")))
        ) );
        //для точной проверки пришлось в InitModel изменить слегка цикл создания жанров,
        //А если быть точным, то пришлось изменить поведение переменной time со статичного на изменяемого в цикле
    }

}
