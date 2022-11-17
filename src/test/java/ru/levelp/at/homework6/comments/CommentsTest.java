package ru.levelp.at.homework6.comments;

import com.github.javafaker.Faker;
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
import ru.levelp.at.homework6.CommentsClient;
import ru.levelp.at.homework6.model.FailResponseData;
import ru.levelp.at.homework6.model.GetAllCommentsResponseData;
import ru.levelp.at.homework6.model.GetCommentsResponseData;
import ru.levelp.at.homework6.model.PostCommentsRequestData;
import ru.levelp.at.homework6.model.PutCommentsRequestData;

public class CommentsTest {

    private CommentsClient commentsClient;

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

        commentsClient = new CommentsClient();
    }

    @Test
    void getAllComments() {

        GetAllCommentsResponseData response = commentsClient.getComments()
                                                            .then()
                                                            .statusCode(200)
                                                            .extract()
                                                            .as(GetAllCommentsResponseData.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getMeta()).isNotNull();
            softly.assertThat(response.getData()).isNotEmpty();
        });
    }

    @Test
    void getComment() {
        var faker = new Faker();
        int id = faker.random().nextInt(100, 104);

        GetCommentsResponseData response = commentsClient.getComment(id)
                                                         .then()
                                                         .statusCode(200)
                                                         .extract()
                                                         .as(GetCommentsResponseData.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getMeta()).isNull();
            softly.assertThat(response.getData().getId()).isEqualTo(id);
            softly.assertThat(response.getData().getPostId()).isNotNull();
        });
    }

    @ParameterizedTest
    @MethodSource("ru.levelp.at.homework6.comments.PositiveDataProvider#dataTest")
    void createComment(int postId, String name, String email, String body) {
        var requestData = PostCommentsRequestData
            .builder()
            .postId(postId)
            .name(name)
            .email(email)
            .body(body)
            .build();

        GetCommentsResponseData response = commentsClient.createComment(requestData)
                                                    .then()
                                                    .statusCode(201)
                                                    .extract()
                                                    .as(GetCommentsResponseData.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getData().getId()).isNotNull();
            softly.assertThat(response.getData().getPostId()).isEqualTo(postId);
            softly.assertThat(response.getData().getName()).isEqualTo(name);
        });
    }

    @ParameterizedTest
    @MethodSource("ru.levelp.at.homework6.comments.PositiveDataProvider#dataTest")
    void changeComments(int postId, String name, String email, String body, int id) {
        var requestData = PutCommentsRequestData
            .builder()
            .postId(postId)
            .name(name)
            .email(email)
            .body(body)
            .build();

        GetCommentsResponseData response = commentsClient.changeComment(id, requestData)
                                                    .then()
                                                    .statusCode(200)
                                                    .extract()
                                                    .as(GetCommentsResponseData.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getData().getId()).isEqualTo(id);
            softly.assertThat(response.getData().getPostId()).isEqualTo(postId);
            softly.assertThat(response.getData().getName()).isEqualTo(name);
        });
    }

    @ParameterizedTest
    @MethodSource("ru.levelp.at.homework6.comments.PositiveDataProvider#dataTest")
    void deleteComments(int postId, String name, String email, String body) {
        var requestData = PostCommentsRequestData
            .builder()
            .postId(postId)
            .name(name)
            .email(email)
            .body(body)
            .build();

        GetCommentsResponseData responseId = commentsClient.createComment(requestData)
                                                         .then()
                                                         .statusCode(201)
                                                         .extract()
                                                         .as(GetCommentsResponseData.class);
        int id = responseId.getData().getId();

        commentsClient.deleteComment(id)
                   .then()
                   .statusCode(204);
    }

    @Test
    void getEmptyComment() {
        var faker = new Faker();
        int id = faker.random().nextInt(10000, 12000);

        GetCommentsResponseData response = commentsClient.getComment(id)
                                                         .then()
                                                         .statusCode(404)
                                                         .extract()
                                                         .as(GetCommentsResponseData.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getMeta()).isNull();
            softly.assertThat(response.getData().getMessage()).isEqualTo("Resource not found");
        });
    }

    @Test
    void postIncorrectParams() {
        var requestData = PostCommentsRequestData
            .builder()
            .postId(0)
            .name("")
            .email("email")
            .body("")
            .build();

        FailResponseData response = commentsClient.createComment(requestData)
                                                  .then()
                                                  .statusCode(422)
                                                  .extract()
                                                  .as(FailResponseData.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getMeta()).isNull();
            softly.assertThat(response.getData().get(0).getField()).isNotEmpty();
            softly.assertThat(response.getData().get(1).getField()).isNotEmpty();
            softly.assertThat(response.getData().get(2).getField()).isNotEmpty();
        });
    }
}
