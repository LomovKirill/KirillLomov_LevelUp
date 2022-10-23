package ru.levelp.at.homework6;

import io.restassured.response.Response;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UsersClient {

    private static final String USERS = "/users";
    private static final String USERS_ID = USERS + "/{id}";
    private static final String AUTH = "Authorization";
    private static final String TOKEN = "Bearer 38a8c4684b5054dcccf3ac1fda8fb4224d4b9dc2d879ddb3d37977900e920cde";

    public Response requestGetUsers() {
        return given()
            .header(AUTH, TOKEN)
            .when()
            .get(USERS)
            .thenReturn();
    }

    public Response requestGetUsers(Map<String, Object> queryParams) {
        return given()
            .queryParams(queryParams)
            .header(AUTH, TOKEN)
            .when()
            .get(USERS)
            .thenReturn();
    }

    public Response requestPostUsers(PostAndPutUsersRequestData postUsersRequestData) {
        return given()
            .header(AUTH, TOKEN)
            .body(postUsersRequestData)
            .when()
            .post(USERS)
            .thenReturn();
    }

    public Response requestGetUsersId(int id, Map<String, Object> queryParams) {
        return given()
            .queryParams(queryParams)
            .header(AUTH, TOKEN)
            .when()
            .get(USERS_ID, id)
            .thenReturn();
    }

    public Response requestPutUsersId(int id, PostAndPutUsersRequestData postUsersRequestData) {
        return given()
            .header(AUTH, TOKEN)
            .body(postUsersRequestData)
            .when()
            .put(USERS_ID, id)
            .thenReturn();
    }

    public Response requestDeleteUsersId(int id) {
        return given()
            .header(AUTH, TOKEN)
            .when()
            .delete(USERS_ID, id)
            .thenReturn();
    }
}
