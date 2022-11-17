package ru.levelp.at.homework6;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import java.util.Map;
import ru.levelp.at.homework6.model.PostAndPutUsersRequestData;

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

    public Response postUsers(PostAndPutUsersRequestData postUsersRequestData) {
        return given()
            .header(AUTH, TOKEN)
            .body(postUsersRequestData)
            .when()
            .post(USERS)
            .thenReturn();
    }

    public Response getUsersId(int id, Map<String, Object> queryParams) {
        return given()
            .queryParams(queryParams)
            .header(AUTH, TOKEN)
            .when()
            .get(USERS_ID, id)
            .thenReturn();
    }

    public Response putUsersId(int id, PostAndPutUsersRequestData postUsersRequestData) {
        return given()
            .header(AUTH, TOKEN)
            .body(postUsersRequestData)
            .when()
            .put(USERS_ID, id)
            .thenReturn();
    }

    public Response deleteUsersId(int id) {
        return given()
            .header(AUTH, TOKEN)
            .when()
            .delete(USERS_ID, id)
            .thenReturn();
    }
}
