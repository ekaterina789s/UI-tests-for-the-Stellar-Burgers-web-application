import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import page.AuthorizationPage;
import page.MainPage;
import page.RegistrationPage;
import static org.junit.Assert.assertNotNull;

public class RegistrationTest extends BaseUITest {

    static Faker faker = new Faker();
    private static String email;
    private static String password;
    private static String name;

    @BeforeClass
    public static void setUp() {
        email = faker.internet().emailAddress();
        password = "password123";
        name = faker.name().fullName();
    }

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

        regPage.writeName(name);
        regPage.writeEmail(email);
        regPage.writePassword(password);

        MainPage mainPageRegisteredUser = regPage.clickButtonRegisterSuccess();

        assertNotNull("Должна открыться главная страница приложения", mainPageRegisteredUser);

    }

    @Test
    @DisplayName("Регистрация с некорректным паролем")
    @Description("Должна появиться ошибка из-за некорректного пароля")
    public void registration_InvalidPasswordTest() {

        MainPage mainPage = new MainPage(driver);

        AuthorizationPage authPage = mainPage.clickButtonPersonalAccount();

        RegistrationPage regPage = authPage.clickLinkRegister();

        String testPassword = "123";

        regPage.writeName(name);
        System.out.println("Name filled: " + name);

        regPage.writeEmail(email);
        System.out.println("Email filled: " + email);

        regPage.writePassword(testPassword);
        System.out.println("Password filled: " + testPassword);

        String errorText = regPage.clickButtonRegisterError();

        Assert.assertEquals("Некорректный пароль", errorText);
    }
}
