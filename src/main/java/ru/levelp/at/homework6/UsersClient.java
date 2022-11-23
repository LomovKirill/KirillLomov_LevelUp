package ru.levelp.at.homework6;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import java.util.Map;
import ru.levelp.at.homework6.model.PostUsersRequestData;
import ru.levelp.at.homework6.model.PutUsersRequestData;

public class UsersClient {

    private static final String USERS = "/users";
    private static final String USERS_ID = USERS + "/{id}";
    private static final String AUTH = "Authorization";
    private static final String TOKEN = "Bearer " + GetProperties.getProperty("token");

    public Response getUsers() {
        return given()
            .header(AUTH, TOKEN)
            .when()
            .get(USERS)
            .thenReturn();
    }

    public Response getUsers(Map<String, Object> queryParams) {
        return given()
            .queryParams(queryParams)
            .header(AUTH, TOKEN)
            .when()
            .get(USERS)
            .thenReturn();
    }

    public Response createUser(PostUsersRequestData postUsersRequestData) {
        return given()
            .header(AUTH, TOKEN)
            .body(postUsersRequestData)
            .when()
            .post(USERS)
            .thenReturn();
    }

    public Response getUser(int id, Map<String, Object> queryParams) {
        return given()
            .queryParams(queryParams)
            .header(AUTH, TOKEN)
            .when()
            .get(USERS_ID, id)
            .thenReturn();
    }

    public Response changeUser(int id, PutUsersRequestData putUsersRequestData) {
        return given()
            .header(AUTH, TOKEN)
            .body(putUsersRequestData)
            .when()
            .put(USERS_ID, id)
            .thenReturn();
    }

    public Response deleteUser(int id) {
        return given()
            .header(AUTH, TOKEN)
            .when()
            .delete(USERS_ID, id)
            .thenReturn();
    }
}
