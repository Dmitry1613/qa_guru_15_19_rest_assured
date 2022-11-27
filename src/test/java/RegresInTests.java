import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class RegresInTests {

    @Test
    void apiPutTest() {
        String data = "{\"name\": \"morpheus\",\"job\": \"zion resident\"}";
        given()
                .contentType(JSON)
                .body(data)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"), "job", is("zion resident"));
    }

    @Test
    void apiDeleteTest() {
        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

    @Test
    void apiPostSuccessfulLoginTest() {
        String data = "{\"email\": \"eve.holt@reqres.in\",\"password\": \"cityslicka\"}";
        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void apiPostUnSuccessfulLoginTest() {
        String data = "{\"email\": \"peter@klaven\"}";
        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void apiGetSingleResourceTest() {
        given()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body(
                        "data.id", is(2),
                        "data.name", is("fuchsia rose"),
                        "data.year", is(2001),
                        "data.color", is("#C74375"),
                        "data.pantone_value", is("17-2031"),
                        "support.url", is("https://reqres.in/#support-heading"),
                        "support.text", is("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }
}

