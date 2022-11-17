package ru.levelp.at.homework6;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import ru.levelp.at.homework6.model.PostCommentsRequestData;
import ru.levelp.at.homework6.model.PutCommentsRequestData;

public class CommentsClient {

    private static final String COMMENTS = "/comments";
    private static final String COMMENTS_ID = COMMENTS + "/{id}";
    private static final String AUTH = "Authorization";
    private static final String TOKEN = "Bearer " + GetProperties.getProperty("token");

    public Response getComments() {
        return given()
            .header(AUTH, TOKEN)
            .when()
            .get(COMMENTS)
            .thenReturn();
    }

    public Response createComment(PostCommentsRequestData requestData) {
        return given()
            .header(AUTH, TOKEN)
            .body(requestData)
            .when()
            .post(COMMENTS)
            .thenReturn();
    }

    public Response getComment(int id) {
        return given()
            .header(AUTH, TOKEN)
            .when()
            .get(COMMENTS_ID, id)
            .thenReturn();
    }

    public Response changeComment(int id, PutCommentsRequestData requestData) {
        return given()
            .header(AUTH, TOKEN)
            .body(requestData)
            .when()
            .put(COMMENTS_ID, id)
            .thenReturn();
    }

    public Response deleteComment(int id) {
        return given()
            .header(AUTH, TOKEN)
            .when()
            .delete(COMMENTS_ID, id)
            .thenReturn();
    }
}
