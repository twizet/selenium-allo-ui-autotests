/*
package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.User;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class Copy_UserApiTests {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private final ObjectMapper mapper = new ObjectMapper(); // Jackson

    @Test
    public void createUser_ShouldReturn200() {
        User user = new User(1001, "morefierce", "morefierce", "morefierce", "morefierce", "morefierce", "morefierce", 1);

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body(user)
                .when()
                .post(BASE_URL + "/user")
                .then()
                .statusCode(200)
                .extract().response();

        int code = response.jsonPath().getInt("code");
        assertThat(code, equalTo(200));
    }

    @Test
    public void loginUser_ShouldReturnSuccess() {
        Response response = RestAssured
                .given()
                .queryParam("username", "morefierce")
                .queryParam("password", "morefierce")
                .when()
                .get(BASE_URL + "/user/login")
                .then()
                .statusCode(200)
                .extract().response();

        String message = response.jsonPath().getString("message");
        assertThat(message, containsString("logged in user session"));
    }

    @Test
    public void getUserByUsername_ShouldReturnCorrectUser() throws Exception {
        Response response = RestAssured
                .when()
                .get(BASE_URL + "/user/morefierce")
                .then()
                .statusCode(200)
                .extract().response();

        User returnedUser = mapper.readValue(response.asString(), User.class);
        assertThat(returnedUser.getUsername(), equalTo("morefierce"));
        assertThat(returnedUser.getEmail(), equalTo("morefierce"));
    }

    @Test
    public void updateUser_ShouldReturn200() {
        User updatedUser = new User(1001, "testuser", "Updated", "model.User", "updated@mail.com", "newpass", "987654321", 1);

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body(updatedUser)
                .when()
                .put(BASE_URL + "/user/testuser")
                .then()
                .statusCode(200)
                .extract().response();

        int code = response.jsonPath().getInt("code");
        assertThat(code, equalTo(200));
    }

    @Test
    public void deleteUser_ShouldReturn200() {
        Response response = RestAssured
                .when()
                .delete(BASE_URL + "/user/morefierce")
                .then()
                .statusCode(200)
                .extract().response();

        int code = response.jsonPath().getInt("code");
        assertThat(code, equalTo(200));
    }

    @Test
    public void logoutUser_ShouldReturnSuccessMessage() {
        Response response = RestAssured
                .when()
                .get(BASE_URL + "/user/logout")
                .then()
                .statusCode(200)
                .extract().response();

        String message = response.jsonPath().getString("message");
        assertThat(message.toLowerCase(), containsString("ok"));
    }
}
*/
