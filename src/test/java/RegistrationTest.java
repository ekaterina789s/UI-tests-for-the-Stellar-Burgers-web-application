import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
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
}
