package com.netcracker.musiclibrary;

import com.netcracker.musiclibrary.data.Genre;
import com.netcracker.musiclibrary.data.Track;
import com.netcracker.musiclibrary.model.Model;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import static com.netcracker.musiclibrary.matchers.IsWithName.withName;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

@QuarkusTest
public class FileResourceTest {
    @Inject
    Model model;
    @Test
    public void fileDownloadTest() throws IOException, ClassNotFoundException {
        RestAssured.given()
                .when().post("/genres/addGenres/Хой");

        InputStream inputStream = given()
          .when()
                .get("/file/download").then().extract().asInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ArrayList<Track> tracks = (ArrayList<Track>) objectInputStream.readObject();
        ArrayList<Genre> genres = (ArrayList<Genre>) objectInputStream.readObject();
        objectInputStream.close();

        assertThat(model.getGenresCollection(), hasItems(withName("Хой")));
    }
}
