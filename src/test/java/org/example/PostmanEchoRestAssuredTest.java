package org.example;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class PostmanEchoRestAssuredTest {

    static {
        baseURI = "https://postman-echo.com";
    }

    @Test
    void testGetRequest() {
        given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"));
    }

    @Test
    void testPostJsonRequest() {
        Map<String, Object> body = new HashMap<>();
        body.put("name", "Amir");
        body.put("course", "QA Engineer");

        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("json.name", equalTo("Amir"))
                .body("json.course", equalTo("QA Engineer"));
    }

    @Test
    void testPostFormDataRequest() {
        given()
                .contentType(ContentType.URLENC)
                .formParam("username", "amirrr24")
                .formParam("role", "student")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("form.username", equalTo("amirrr24"))
                .body("form.role", equalTo("student"));
    }

    @Test
    void testPutRequest() {
        Map<String, Object> body = new HashMap<>();
        body.put("id", 1);
        body.put("status", "updated");

        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("json.id", equalTo(1))
                .body("json.status", equalTo("updated"));
    }

    @Test
    void testPatchRequest() {
        Map<String, Object> body = new HashMap<>();
        body.put("patch", "data");
        body.put("partial", true);

        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("json.patch", equalTo("data"))
                .body("json.partial", equalTo(true));
    }

    @Test
    void testDeleteRequest() {
        Map<String, Object> body = new HashMap<>();
        body.put("id", 42);

        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("json.id", equalTo(42));
    }
}