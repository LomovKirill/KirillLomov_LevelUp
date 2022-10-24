package ru.levelp.at.homework6.posts;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.levelp.at.homework6.PostsClient;
import ru.levelp.at.homework6.model.GetUsersResponseData;
import ru.levelp.at.homework6.model.PostPostsRequestData;

public class PostsNegativeTest {

    private PostsClient postClient;

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

        postClient = new PostsClient();
    }

    @ParameterizedTest
    @MethodSource("ru.levelp.at.homework6.posts.negative.data.provider.PostNegativeDataProvider#dataTest")
    void postIncorrectParams(int userId, String title, String body) {
        var requestData = PostPostsRequestData
            .builder()
            .userId(userId)
            .title(title)
            .body(body)
            .build();

        GetUsersResponseData response =
            postClient.requestPostPosts(requestData)
                      .then()
                      .statusCode(422)
                      .extract()
                      .as(GetUsersResponseData.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getMeta()).isNull();
            softly.assertThat(response.getData().get(0).getField()).isNotEmpty();
        });
    }
}
