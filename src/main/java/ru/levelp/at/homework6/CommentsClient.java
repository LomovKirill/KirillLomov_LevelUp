package ru.levelp.at.homework6;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import ru.levelp.at.homework6.model.PostAndPutCommentsRequestData;

public class CommentsClient {

    private static final String COMMENTS = "/comments";
    private static final String COMMENTS_ID = COMMENTS + "/{id}";
    private static final String AUTH = "Authorization";
    private static final String TOKEN = "Bearer " + GetProperties.getProperty("token");

    public Response requestGet() {
        return given()
            .header(AUTH, TOKEN)
            .when()
            .get(COMMENTS)
            .thenReturn();
    }

    public Response requestPost(PostAndPutCommentsRequestData requestData) {
        return given()
            .header(AUTH, TOKEN)
            .body(requestData)
            .when()
            .post(COMMENTS)
            .thenReturn();
    }

    public Response requestGetId(int id) {
        return given()
            .header(AUTH, TOKEN)
            .when()
            .get(COMMENTS_ID, id)
            .thenReturn();
    }

    public Response requestPutId(int id, PostAndPutCommentsRequestData requestData) {
        return given()
            .header(AUTH, TOKEN)
            .body(requestData)
            .when()
            .put(COMMENTS_ID, id)
            .thenReturn();
    }

    public Response requestDeleteId(int id) {
        return given()
            .header(AUTH, TOKEN)
            .when()
            .delete(COMMENTS_ID, id)
            .thenReturn();
    }
}
