package ru.levelp.at.homework6;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import ru.levelp.at.homework6.model.PostPostsRequestData;

public class PostsClient {

    private static final String POSTS = "/posts";
    private static final String POSTS_ID = POSTS + "/{id}";
    private static final String AUTH = "Authorization";
    private static final String TOKEN = "Bearer " + GetProperties.getProperty("token");

    public Response getPosts() {
        return given()
            .header(AUTH, TOKEN)
            .when()
            .get(POSTS)
            .thenReturn();
    }

    public Response createPost(PostPostsRequestData postUsersRequestData) {
        return given()
            .header(AUTH, TOKEN)
            .body(postUsersRequestData)
            .when()
            .post(POSTS)
            .thenReturn();
    }

    public Response getPost(int id) {
        return given()
            .header(AUTH, TOKEN)
            .when()
            .get(POSTS_ID, id)
            .thenReturn();
    }

    public Response changePost(int id, PostPostsRequestData postUsersRequestData) {
        return given()
            .header(AUTH, TOKEN)
            .body(postUsersRequestData)
            .when()
            .put(POSTS_ID, id)
            .thenReturn();
    }

    public Response deletePost(int id) {
        return given()
            .header(AUTH, TOKEN)
            .when()
            .delete(POSTS_ID, id)
            .thenReturn();
    }
}
