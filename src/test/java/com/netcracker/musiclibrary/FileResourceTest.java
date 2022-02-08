package com.netcracker.musiclibrary;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class FileResourceTest {
    @Test
    public void testHelloEndpoint() {

        given()
          .when()
                .post("/file/upload")
          .then()
             .statusCode(201);
    }
}
