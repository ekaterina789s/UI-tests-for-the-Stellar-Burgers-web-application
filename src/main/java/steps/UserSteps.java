package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import user.UserModel;

import static api.BaseUriData.CREATE_USER_PATH;
import static api.BaseUriData.DELETE_USER_PATH;
import static io.restassured.RestAssured.given;
import static user.UserData.currentAccessToken;

public class UserSteps {

    @Step("Создание уникального пользователя")
    public static Response createUniqueUser(UserModel user){
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(CREATE_USER_PATH)
                .then()
                .extract().response();
    }

    @Step("Удаление пользователя по accessToken, который приходит при создании пользователя")
    public static Response deleteUser(UserModel user){
        return given()
                .when()
                .header("Authorization", "Bearer " + currentAccessToken)
                .delete(DELETE_USER_PATH);
    }
}
