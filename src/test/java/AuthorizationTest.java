import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import page.AuthorizationPage;
import page.MainPage;
import page.RecoverPasswordPage;
import page.RegistrationPage;
import user.UserData;
import user.UserModel;

import static api.BaseUriData.BASE_URI;
import static steps.UserSteps.createUniqueUser;
import static steps.UserSteps.deleteUser;

public class AuthorizationTest extends BaseUITest {

    static Faker faker = new Faker();
    static UserModel userModel;
    private static String email;
    private static String password;
    private static String name;
    static Response response;

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URI;
        email = faker.internet().emailAddress();
        password = "password123";
        name = faker.name().fullName();
        userModel = new UserModel(email, password, name);
        response = createUniqueUser(userModel);
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Пользователь должен успешно авторизоваться")
    public void authorization_EnterAccountButtonTest() {

        MainPage mainPage = new MainPage(driver);
        AuthorizationPage authPage = mainPage.clickButtonEnterAccount();

        authPage.fillEmailAndPasswordAuthPage(email, password);

        // Клик и проверка
        MainPage mainPageAuthorizedUser = authPage.clickButtonEnter();
        Assert.assertNotNull("Должна открыться главная страница", mainPageAuthorizedUser);
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет")
    @Description("Пользователь должен успешно авторизоваться")
    public void authorization_PersonalAccountButtonTest() {

        MainPage mainPage = new MainPage(driver);
        AuthorizationPage authPage = mainPage.clickButtonPersonalAccount();

        authPage.fillEmailAndPasswordAuthPage(email, password);
        MainPage mainPageAuthorizedUser = authPage.clickButtonEnter();

        Assert.assertNotNull("Должна открыться главная страница", mainPageAuthorizedUser);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Пользователь должен успешно авторизоваться")
    public void authorization_ButtonRegistrationFormTest() {

        MainPage mainPage = new MainPage(driver);
        AuthorizationPage authPage = mainPage.clickButtonEnterAccount();

        RegistrationPage regPage = authPage.clickLinkRegister();

        AuthorizationPage authPageFromRegistrationPage = regPage.clickLinkEnter();

        authPageFromRegistrationPage.fillEmailAndPasswordAuthPage(email, password);

        MainPage mainPageAuthorizedUser = authPageFromRegistrationPage.clickButtonEnter();

        Assert.assertNotNull("Должна открыться главная страница", mainPageAuthorizedUser);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Пользователь должен успешно авторизоваться")
    public void authorization_PasswordRecoveryFormTest() {

        MainPage mainPage = new MainPage(driver);
        AuthorizationPage authPage = mainPage.clickButtonEnterAccount();

        RecoverPasswordPage recPassword = authPage.clickLinkRecoverPassword();

        AuthorizationPage authPageFromPasswordRecoveryForm = recPassword.clickLinkEnterRecoverPas();

        authPageFromPasswordRecoveryForm.fillEmailAndPasswordAuthPage(email, password);

        MainPage mainPageAuthorizedUser = authPageFromPasswordRecoveryForm.clickButtonEnter();

        Assert.assertNotNull("Должна открыться главная страница", mainPageAuthorizedUser);
    }

    @After
    public void tearDown(){
        if (response != null) {
            String accessToken = response.jsonPath().getString("accessToken");
            UserData.currentAccessToken = accessToken;
            System.out.println("Удаляем пользователя с accessToken: " + UserData.currentAccessToken);
            deleteUser(userModel);
        }
    }
}
