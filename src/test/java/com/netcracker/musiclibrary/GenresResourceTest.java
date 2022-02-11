package com.netcracker.musiclibrary;

import com.netcracker.musiclibrary.data.Genre;
import com.netcracker.musiclibrary.model.Model;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@QuarkusTest
public class GenresResourceTest {
    @Inject
    Model model;

    @Test
    public void getGenresTest() {
        Genre genre = RestAssured.given()
                .contentType(ContentType.JSON)
                .when().get("/genres")
                .then().statusCode(200)
                .extract().jsonPath().getObject("[0]", Genre.class);

        assertThat(genre.name(), equalTo("Рок"));
    }

    @Test
    public void addGenresTest() {
        RestAssured.given()
                .when().post("/genres/addGenres/Поп")
                .then().statusCode(201);

        model.isContainedInGenres(model.getGenre("Поп"));
    }

    @Test
    public void deleteGenresTest() {
        RestAssured.given()
                .when().delete("/genres/removeGenres/Шанси")
                .then().statusCode(200);

        not(model.isContainedInGenres(model.getGenre("Шанси")));
    }
}
