package ru.levelp.at.homework6.users;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import java.util.Map;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.levelp.at.homework6.UsersClient;
import ru.levelp.at.homework6.model.GetUsersResponseData;
import ru.levelp.at.homework6.model.PostAndPutUsersRequestData;
import ru.levelp.at.homework6.model.PostUsersResponseData;

public class UsersNegativeTest {

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

    @ParameterizedTest
    @MethodSource("ru.levelp.at.homework6.users.negative.data.provider.GetNegativeDataProvider#dataTest")
    void getEmptyUser(String name, String email, String gender, String status) {

        GetUsersResponseData response =
            usersClient.requestGetUsers(Map.of("name", name, "email", email, "gender", gender, "status", status))
                       .then()
                       .statusCode(200)
                       .extract()
                       .as(GetUsersResponseData.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getMeta()).isNotNull();
            softly.assertThat(response.getMeta().getPagination().getTotal()).isEqualTo(0);
            softly.assertThat(response.getData()).isEmpty();
        });
    }

    @ParameterizedTest
    @MethodSource("ru.levelp.at.homework6.users.negative.data.provider.PostNegativeDataProvider#dataTest")
    void postIncorrectParams(String name, String email, String gender, String status, String field,
                             String message) {
        var requestData = PostAndPutUsersRequestData
            .builder()
            .email(email)
            .gender(gender)
            .name(name)
            .status(status)
            .build();

        GetUsersResponseData response =
            usersClient.requestPostUsers(requestData)
                       .then()
                       .statusCode(422)
                       .extract()
                       .as(GetUsersResponseData.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getMeta()).isNull();
            softly.assertThat(response.getData().get(0).getField()).isEqualTo(field);
            softly.assertThat(response.getData().get(0).getMessage()).isEqualTo(message);
        });
    }

    @ParameterizedTest
    @MethodSource("ru.levelp.at.homework6.users.negative.data.provider.PutNegativeDataProvider#dataTest")
    void putIncorrectParams(String name, String email, String gender, String status, String field,
                            String message, int id) {
        var requestData = PostAndPutUsersRequestData
            .builder()
            .email(email)
            .gender(gender)
            .name(name)
            .status(status)
            .build();

        GetUsersResponseData response =
            usersClient.requestPutUsersId(id, requestData)
                       .then()
                       .statusCode(422)
                       .extract()
                       .as(GetUsersResponseData.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getMeta()).isNull();
            softly.assertThat(response.getData().get(0).getField()).isEqualTo(field);
            softly.assertThat(response.getData().get(0).getMessage()).isEqualTo(message);
        });
    }

    @Test
    void deleteEmptyUser() {
        var faker = new Faker();

        PostUsersResponseData response =
            usersClient.requestDeleteUsersId(faker.number().numberBetween(10000, 11111))
                       .then()
                       .statusCode(404)
                       .extract()
                       .as(PostUsersResponseData.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getMeta()).isNull();
            softly.assertThat(response.getData().getMessage()).isNotEmpty();
        });
    }
}
