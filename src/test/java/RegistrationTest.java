import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import page.MainPage;

import static org.junit.Assert.assertNotNull;

public class RegistrationTest extends BaseUITest {

    @Test
    @DisplayName("Переход на страницу авторизации")
    @Description("Переход с главной страницы")
    public void goToAuthorizationPageTest() {

        MainPage mainPage = new MainPage(driver);

        AuthorizationPage authPage = mainPage.clickButtonPersonalAccount();

        assertNotNull("Должна открыться страница авторизации", authPage);
    }

    @Test
    @DisplayName("Переход на страницу регистрации")
    @Description("Переход со страницы авторизации")
    public void goToRegistrationPageTest() {

        MainPage mainPage = new MainPage(driver);

        AuthorizationPage authPage = mainPage.clickButtonPersonalAccount();

        RegistrationPage regPage = authPage.clickLinkRegister();

        assertNotNull("Должна открыться страница регистрации", regPage);

    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Должна открыться главная страница пользователя после успешной регистрации")
    public void registration_SuccessTest() {

        MainPage mainPage = new MainPage(driver);

        AuthorizationPage authPage = mainPage.clickButtonPersonalAccount();

        RegistrationPage regPage = authPage.clickLinkRegister();

        //заполнение полей и клик по кнопке "Зарегистрироваться"

        Faker faker = new Faker();

        String testName = faker.name().fullName();
        String testEmail = faker.internet().emailAddress();

        regPage.writeName(testName);
        regPage.writeEmail(testEmail);
        regPage.writePassword(RegistrationPage.password);

        MainPage mainPage2 = regPage.clickButtonRegister_Success();

        assertNotNull("Должна открыться главная страница приложения", mainPage2);

    }

    @Test
    @DisplayName("Регистрация с некорректным паролем")
    @Description("Должна появиться ошибка из-за некорректного пароля")
    public void registration_InvalidPasswordTest() {

        MainPage mainPage = new MainPage(driver);

        AuthorizationPage authPage = mainPage.clickButtonPersonalAccount();

        RegistrationPage regPage = authPage.clickLinkRegister();

        //заполнение полей и клик по кнопке "Зарегистрироваться"

        Faker faker = new Faker();

        String testName = faker.name().fullName();
        String testEmail = faker.internet().emailAddress();
        String testPassword = "123";

        regPage.writeName(testName);
        System.out.println("Name filled: " + testName);

        regPage.writeEmail(testEmail);
        System.out.println("Email filled: " + testEmail);

        regPage.writePassword(testPassword);
        System.out.println("Password filled: " + testPassword);

        String errorText = regPage.clickButtonRegister_Error();

        Assert.assertEquals("Некорректный пароль", errorText);
    }
}
