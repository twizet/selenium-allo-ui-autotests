
package tests;

import api.UserApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import model.ApiResponse;
import org.junit.jupiter.api.Test;
import utils.IdGenerator;

import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserApiTests {

    private final UserApi userApi = new UserApi();
    private final ObjectMapper mapper = new ObjectMapper();
    private final String username = "morefierce";
    private final long userId = IdGenerator.generateId();

    @Test
    public void createUser_ShouldReturnDetailedResponse() {
        User user = new User(userId, username, "John", "Doe",
                "john@example.com", "pass123", "123456", 1);

        Response response = userApi.createUser(user);
        assertThat(response.statusCode(), equalTo(200));

        ApiResponse body = response.as(ApiResponse.class);
        System.out.println("Swagger Message: " + body.getMessage());

        assertThat(body.getCode(), equalTo(200));
        assertThat(body.getMessage(), not(emptyOrNullString()));
    }

    @Test
    public void loginUser_ShouldReturnDetailedMessage() {
        Response response = userApi.loginUser(username, "pass123");
        assertThat(response.statusCode(), equalTo(200));

        ApiResponse body = response.as(ApiResponse.class);
        System.out.println("Login Message: " + body.getMessage());

        assertThat(body.getMessage(), containsString("logged in user session"));
    }

    @Test
    public void getUser_ShouldReturnCorrectFields() throws Exception {
        Response response = userApi.getUserByUsername(username);
        assertThat(response.statusCode(), equalTo(200));

        User user = response.as(User.class);
        System.out.println("User Email: " + user.getEmail());
        assertThat(user.getUsername(), equalTo(username));
    }

    @Test
    public void updateUser_ShouldReturnUpdatedMessage() {
        User updated = new User(userId, username, "Jane", "Doe",
                "jane@example.com", "newpass", "987654", 1);

        Response response = userApi.updateUser(username, updated);
        assertThat(response.statusCode(), equalTo(200));

        ApiResponse body = response.as(ApiResponse.class);
        System.out.println("Update Message: " + body.getMessage());

        assertThat(body.getCode(), equalTo(200));
    }

    @Test
    public void logoutUser_ShouldReturnOk() {
        Response response = userApi.logoutUser();
        assertThat(response.statusCode(), equalTo(200));

        ApiResponse body = response.as(ApiResponse.class);
        System.out.println("Logout Message: " + body.getMessage());

        assertThat(body.getMessage().toLowerCase(), containsString("ok"));
    }

    @Test
    public void deleteUser_ShouldReturnDeletedMessage() {
        Response response = userApi.deleteUser(username);
        assertThat(response.statusCode(), equalTo(200));

        ApiResponse body = response.as(ApiResponse.class);
        System.out.println("Delete Message: " + body.getMessage());

        assertThat(body.getCode(), equalTo(200));
    }
}

