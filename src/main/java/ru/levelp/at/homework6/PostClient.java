package ru.levelp.at.homework6;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostClient {

    private static final String POSTS = "/posts";
    private static final String POSTS_ID = POSTS + "/{id}";
    private static final String AUTH = "Authorization";
    private static final String TOKEN = "Bearer 38a8c4684b5054dcccf3ac1fda8fb4224d4b9dc2d879ddb3d37977900e920cde";

    public Response requestGetPosts() {
        return given()
            .header(AUTH, TOKEN)
            .when()
            .get(POSTS)
            .thenReturn();
    }

    public Response requestPostPosts(PostPostsRequestData requestData) {
        return given()
            .header(AUTH, TOKEN)
            .body(requestData)
            .when()
            .post(POSTS)
            .thenReturn();
    }

    public Response requestGetPostId(int id) {
        return given()
            .header(AUTH, TOKEN)
            .when()
            .get(POSTS_ID, id)
            .thenReturn();
    }

    public Response requestPutPostId(int id, PostPostsRequestData requestData) {
        return given()
            .header(AUTH, TOKEN)
            .body(requestData)
            .when()
            .put(POSTS_ID, id)
            .thenReturn();
    }

    public Response requestDeletePostId(int id) {
        return given()
            .header(AUTH, TOKEN)
            .when()
            .delete(POSTS_ID, id)
            .thenReturn();
    }
}
