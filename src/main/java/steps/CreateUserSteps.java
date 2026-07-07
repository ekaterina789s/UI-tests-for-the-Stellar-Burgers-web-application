package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import user.UserModel;

import static api.BaseUriData.CREATE_USER_PATH;
import static io.restassured.RestAssured.given;

public class CreateUserSteps {

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
}
