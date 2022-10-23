package ru.levelp.at.homework6;

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
import java.util.Map;

public class UsersPositiveTest {

    private UsersClient usersClient;

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

        usersClient = new UsersClient();
    }

    @Test
    void getAllUsers() {

        GetUsersResponseData response = usersClient.requestGetUsers()
                                                   .then()
                                                   .statusCode(200)
                                                   .extract()
                                                   .as(GetUsersResponseData.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getMeta()).isNotNull();
            softly.assertThat(response.getData()).isNotNull();
        });
    }

    @ParameterizedTest
    @MethodSource("ru.levelp.at.homework6.GetPositiveDataProvider#dataTest")
    void getUser(String name, String email, String gender, String status) {

        GetUsersResponseData response =
            usersClient.requestGetUsers(Map.of("name", name, "email", email, "gender", gender, "status", status))
                       .then()
                       .statusCode(200)
                       .extract()
                       .as(GetUsersResponseData.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getMeta()).isNotNull();
            softly.assertThat(response.getData().get(0).getId()).isNotNull();
            softly.assertThat(response.getData().get(0).getName()).isEqualTo(name);
            softly.assertThat(response.getData().get(0).getEmail()).isEqualTo(email);
            softly.assertThat(response.getData().get(0).getGender()).isEqualTo(gender);
            softly.assertThat(response.getData().get(0).getStatus()).isEqualTo(status);
        });
    }

    @ParameterizedTest
    @MethodSource("ru.levelp.at.homework6.PostAndPutPositiveDataProvider#dataTest")
    void createUsers(String name, String email, String gender, String status) {
        var requestData = PostAndPutUsersRequestData
            .builder()
            .email(email)
            .gender(gender)
            .name(name)
            .status(status)
            .build();

        PostUsersResponseData response = usersClient.requestPostUsers(requestData)
                                                    .then()
                                                    .statusCode(201)
                                                    .extract()
                                                    .as(PostUsersResponseData.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getData().getId()).isNotNull();
            softly.assertThat(response.getData().getGender()).isEqualTo(gender);
            softly.assertThat(response.getData().getName()).isEqualTo(requestData.getName());
            softly.assertThat(response.getData().getEmail()).isEqualTo(requestData.getEmail());
            softly.assertThat(response.getData().getStatus()).isEqualTo(requestData.getStatus());
        });
    }

    @ParameterizedTest
    @MethodSource("ru.levelp.at.homework6.GetPositiveDataProvider#dataTest")
    void getUserId(String name, String email, String gender, String status, int id) {

        PostUsersResponseData response =
            usersClient.requestGetUsersId(id, Map.of("name", name, "email", email, "gender", gender, "status", status))
                       .then()
                       .statusCode(200)
                       .extract()
                       .as(PostUsersResponseData.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getData().getId()).isEqualTo(id);
            softly.assertThat(response.getData().getName()).isEqualTo(name);
            softly.assertThat(response.getData().getEmail()).isEqualTo(email);
            softly.assertThat(response.getData().getGender()).isEqualTo(gender);
            softly.assertThat(response.getData().getStatus()).isEqualTo(status);
        });
    }

    @ParameterizedTest
    @MethodSource("ru.levelp.at.homework6.PostAndPutPositiveDataProvider#dataTest")
    void changeUsers(String name, String email, String gender, String status, int id) {
        var requestData = PostAndPutUsersRequestData
            .builder()
            .email(email)
            .gender(gender)
            .name(name)
            .status(status)
            .build();

        PostUsersResponseData response = usersClient.requestPutUsersId(id, requestData)
                                                    .then()
                                                    .statusCode(200)
                                                    .extract()
                                                    .as(PostUsersResponseData.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getData().getId()).isEqualTo(id);
            softly.assertThat(response.getData().getGender()).isEqualTo(gender);
            softly.assertThat(response.getData().getName()).isEqualTo(requestData.getName());
            softly.assertThat(response.getData().getEmail()).isEqualTo(requestData.getEmail());
            softly.assertThat(response.getData().getStatus()).isEqualTo(requestData.getStatus());
        });
    }

    @ParameterizedTest
    @MethodSource("ru.levelp.at.homework6.DeletePositiveDataProvider#dataTest")
    void deleteUsers(String name, String email, String gender, String status) {

        var requestData = PostAndPutUsersRequestData
            .builder()
            .email(email)
            .gender(gender)
            .name(name)
            .status(status)
            .build();

        PostUsersResponseData responseId = usersClient.requestPostUsers(requestData)
                                                      .then()
                                                      .statusCode(201)
                                                      .extract()
                                                      .as(PostUsersResponseData.class);
        int id = responseId.getData().getId();

        usersClient.requestDeleteUsersId(id)
                   .then()
                   .statusCode(204);
    }
}
