package com.netcracker.musiclibrary;

import com.netcracker.musiclibrary.data.Genre;
import com.netcracker.musiclibrary.model.Model;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.List;

import static com.netcracker.musiclibrary.matchers.IsWithName.withName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@QuarkusTest
public class GenresResourceTest {
    @Inject
    Model model;

    @Test
    public void getGenresTest() {
        List<Genre> genresList = RestAssured.given()
                .contentType(ContentType.JSON)
                .when().get("/genres")
                .then().statusCode(200)
                .extract().jsonPath().getList("", Genre.class);

        assertThat(genresList, hasItems(withName("Рок")));
    }

    @Test
    public void addGenresTest() {
        RestAssured.given()
                .when().post("/genres/addGenres/Поп")
                .then().statusCode(201);

        assertThat(model.getGenresCollection(), hasItems(withName("Поп")));
    }

    @Test
    public void deleteGenresTest() {
        RestAssured.given()
                .when().delete("/genres/removeGenres/Шанси")
                .then().statusCode(200);

        assertThat(model.getGenresCollection(), not(hasItems(withName("Шанси"))));
    }
}
