import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import page.AuthorizationPage;
import page.MainPage;
import page.RecoverPasswordPage;
import page.RegistrationPage;
import user.UserModel;

import static api.BaseUriData.BASE_URI;
import static steps.CreateUserSteps.createUniqueUser;

public class AuthorizationTest extends BaseUITest {

    Faker faker = new Faker();

    UserModel userModel;

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URI;
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Пользователь должен успешно авторизоваться")
    public void authorization_EnterAccountButtonTest() {
        // Генерация данных
        String email = faker.internet().emailAddress();
        String password = "password123";
        String name = faker.name().fullName();

        // Создание пользователя через API
        userModel = new UserModel(email, password, name);
        var response = createUniqueUser(userModel);

        // UI Действия
        MainPage mainPage = new MainPage(driver);
        AuthorizationPage authPage = mainPage.clickButtonEnterAccount();

        authPage.fillEmailAndPassword_AuthPage(email, password);

        // Клик и проверка
        MainPage mainPage2 = authPage.clickButtonEnter();

        Assert.assertNotNull("Должна открыться главная страница", mainPage2);
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет")
    @Description("Пользователь должен успешно авторизоваться")
    public void authorization_PersonalAccountButtonTest() {
        String email = faker.internet().emailAddress();
        String password = "password123";
        String name = faker.name().fullName();

        userModel = new UserModel(email, password, name);
        var response = createUniqueUser(userModel);

        MainPage mainPage = new MainPage(driver);
        AuthorizationPage authPage = mainPage.clickButtonPersonalAccount();

        authPage.fillEmailAndPassword_AuthPage(email, password);
        MainPage mainPage2 = authPage.clickButtonEnter();

        Assert.assertNotNull("Должна открыться главная страница", mainPage2);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Пользователь должен успешно авторизоваться")
    public void authorization_ButtonRegistrationFormTest() {
        String email = faker.internet().emailAddress();
        String password = "password123";
        String name = faker.name().fullName();

        userModel = new UserModel(email, password, name);
        var response = createUniqueUser(userModel);

        MainPage mainPage = new MainPage(driver);
        AuthorizationPage authPage = mainPage.clickButtonEnterAccount();

        RegistrationPage regPage = authPage.clickLinkRegister();

        AuthorizationPage authPage2 = regPage.clickLinkEnter();

        authPage2.fillEmailAndPassword_AuthPage(email, password);

        MainPage mainPage2 = authPage2.clickButtonEnter();

        Assert.assertNotNull("Должна открыться главная страница", mainPage2);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Пользователь должен успешно авторизоваться")
    public void authorization_PasswordRecoveryFormTest() {
        String email = faker.internet().emailAddress();
        String password = "password123";
        String name = faker.name().fullName();

        userModel = new UserModel(email, password, name);
        var response = createUniqueUser(userModel);

        MainPage mainPage = new MainPage(driver);
        AuthorizationPage authPage = mainPage.clickButtonEnterAccount();

        RecoverPasswordPage recPassword = authPage.clickLinkRecoverPassword();

        AuthorizationPage authPage2 = recPassword.clickLinkEnter_RecoverPas();

        authPage2.fillEmailAndPassword_AuthPage(email, password);

        MainPage mainPage2 = authPage2.clickButtonEnter();

        Assert.assertNotNull("Должна открыться главная страница", mainPage2);
    }
}
