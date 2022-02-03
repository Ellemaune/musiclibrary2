package com.netcracker.musiclibrary;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.path.json.JsonPath;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.path.json.JsonPath;

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
