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
import ru.levelp.at.homework6.model.FailResponseData;
import ru.levelp.at.homework6.model.GetUsersResponseData;
import ru.levelp.at.homework6.model.PostUsersRequestData;
import ru.levelp.at.homework6.model.PostUsersResponseData;
import ru.levelp.at.homework6.model.PutUsersRequestData;

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
            usersClient.getUsers(Map.of("name", name, "email", email, "gender", gender, "status", status))
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
        var requestData = PostUsersRequestData
            .builder()
            .email(email)
            .gender(gender)
            .name(name)
            .status(status)
            .build();

        FailResponseData response =
            usersClient.createUser(requestData)
                       .then()
                       .statusCode(422)
                       .extract()
                       .as(FailResponseData.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getMeta()).isNull();
            softly.assertThat(response.getData().get(0).getField()).isEqualTo(field);
            softly.assertThat(response.getData().get(0).getMessage()).isEqualTo(message);
        });
    }

    @ParameterizedTest
    @MethodSource("ru.levelp.at.homework6.users.negative.data.provider.PutNegativeDataProvider#dataTest")
    void putIncorrectParams(String name, String email, String gender, String status, String field,
                            String message) {
        var faker = new Faker();
        var requestData = PostUsersRequestData
            .builder()
            .email(faker.internet().emailAddress())
            .gender("male")
            .name(faker.name().fullName())
            .status("active")
            .build();

        PostUsersResponseData responseId = usersClient.createUser(requestData)
                                                    .then()
                                                    .extract()
                                                    .as(PostUsersResponseData.class);

        int id = responseId.getData().getId();

        var requestPutData = PutUsersRequestData
            .builder()
            .email(email)
            .gender(gender)
            .name(name)
            .status(status)
            .build();

        FailResponseData response =
            usersClient.changeUser(id, requestPutData)
                       .then()
                       .statusCode(422)
                       .extract()
                       .as(FailResponseData.class);

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
            usersClient.deleteUser(faker.number().numberBetween(100000, 111110))
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
