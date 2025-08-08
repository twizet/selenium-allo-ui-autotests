package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.User;

import static io.restassured.RestAssured.given;

public class UserApi {
    private final String BASE_URL = "https://petstore.swagger.io/v2";

    public Response createUser(User user) {
        return given()
                .contentType("application/json")
                .body(user)
                .when()
                .post(BASE_URL + "/user")
                .then()
                .log().body() // лог повного респонсу
                .extract().response();
    }

    public Response loginUser(String username, String password) {
        return given()
                .queryParam("username", username)
                .queryParam("password", password)
                .when()
                .get(BASE_URL + "/user/login")
                .then()
                .log().body()
                .extract().response();
    }

    public Response getUserByUsername(String username) {
        return RestAssured
                .get(BASE_URL + "/user/" + username)
                .then()
                .log().body()
                .extract().response();
    }

    public Response updateUser(String username, User user) {
        return given()
                .contentType("application/json")
                .body(user)
                .when()
                .put(BASE_URL + "/user/" + username)
                .then()
                .log().body()
                .extract().response();
    }

    public Response deleteUser(String username) {
        return RestAssured
                .delete(BASE_URL + "/user/" + username)
                .then()
                .log().body()
                .extract().response();
    }

    public Response logoutUser() {
        return RestAssured
                .get(BASE_URL + "/user/logout")
                .then()
                .log().body()
                .extract().response();
    }
}
