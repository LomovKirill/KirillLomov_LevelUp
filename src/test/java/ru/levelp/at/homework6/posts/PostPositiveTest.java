package ru.levelp.at.homework6.posts;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.levelp.at.homework6.GetPostsResponseData;
import ru.levelp.at.homework6.PostClient;
import ru.levelp.at.homework6.PostPostsRequestData;
import ru.levelp.at.homework6.PostPostsResponseData;

public class PostPositiveTest {

    private PostClient postClient;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v1";

        var requestSpecification = new RequestSpecBuilder()
            .log(LogDetail.ALL)
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .build();

        var responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .build();

        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;

        postClient = new PostClient();
    }

    @Test
    void getAllPosts() {

        GetPostsResponseData response = postClient.requestGetPosts()
                                                  .then()
                                                  .statusCode(200)
                                                  .extract()
                                                  .as(GetPostsResponseData.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getMeta()).isNotNull();
            softly.assertThat(response.getData()).isNotNull();
        });
    }

    @ParameterizedTest
    @MethodSource("ru.levelp.at.homework6.posts.PostPositiveDataProvider#dataTest")
    void createPost(int userId, String title, String body) {

        var requestData = PostPostsRequestData
            .builder()
            .userId(userId)
            .title(title)
            .body(body)
            .build();

        PostPostsResponseData response = postClient.requestPostPosts(requestData)
                                                   .then()
                                                   .statusCode(201)
                                                   .extract()
                                                   .as(PostPostsResponseData.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getMeta()).isNull();
            softly.assertThat(response.getData().getUserId()).isEqualTo(userId);
            softly.assertThat(response.getData().getTitle()).isEqualTo(title);
            softly.assertThat(response.getData().getBody()).isEqualTo(body);
        });
    }

    @ParameterizedTest
    @MethodSource("ru.levelp.at.homework6.posts.GetPositiveDataProvider#dataTest")
    void getPost(int id) {

        PostPostsResponseData response = postClient.requestGetPostId(id)
                                                  .then()
                                                  .statusCode(200)
                                                  .extract()
                                                  .as(PostPostsResponseData.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getMeta()).isNull();
            softly.assertThat(response.getData().getId()).isEqualTo(id);
        });
    }

    @ParameterizedTest
    @MethodSource("ru.levelp.at.homework6.posts.PutPositiveDataProvider#dataTest")
    void changePost(int id, int userId, String title, String body) {

        var requestData = PostPostsRequestData
            .builder()
            .userId(userId)
            .title(title)
            .body(body)
            .build();

        PostPostsResponseData response = postClient.requestPutPostId(id, requestData)
                                                   .then()
                                                   .statusCode(200)
                                                   .extract()
                                                   .as(PostPostsResponseData.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getMeta()).isNull();
            softly.assertThat(response.getData().getId()).isEqualTo(id);
            softly.assertThat(response.getData().getUserId()).isEqualTo(userId);
            softly.assertThat(response.getData().getTitle()).isEqualTo(title);
            softly.assertThat(response.getData().getBody()).isEqualTo(body);
        });
    }

    @ParameterizedTest
    @MethodSource("ru.levelp.at.homework6.posts.PostPositiveDataProvider#dataTest")
    void deletePost(int userId, String title, String body) {

        var requestData = PostPostsRequestData
            .builder()
            .userId(userId)
            .title(title)
            .body(body)
            .build();

        PostPostsResponseData responseId = postClient.requestPostPosts(requestData)
                                                   .then()
                                                   .statusCode(201)
                                                   .extract()
                                                   .as(PostPostsResponseData.class);

        int id =  responseId.getData().getId();

        postClient.requestDeletePostId(id)
                   .then()
                   .statusCode(204);
    }
}
