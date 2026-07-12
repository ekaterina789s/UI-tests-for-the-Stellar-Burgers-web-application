package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    private final WebDriver driver;

    private final WebDriverWait wait;

    public String name;

    public String email;


    //локатор поля "Имя"
    private static final By NAME_FIELD = By.xpath("//div[.//label[text()='Имя']]//input[@class='text input__textfield text_type_main-default']");

    //локатор поля "Email"
    private static final By EMAIL_FIELD = By.xpath("//div[.//label[text()='Email']]//input[@class='text input__textfield text_type_main-default']");

    //локатор поля "Пароль"
    private static final By PASSWORD_FIELD = By.xpath("//div[.//label[text()='Пароль']]//input[@class='text input__textfield text_type_main-default']");

    //локатор кнопки "Зарегистрироваться"
    private static final By BUTTON_REGISTER = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa'][text()='Зарегистрироваться']");

    //локатор ошибки "Некорректный пароль"
    private static final By ERROR_INVALID_PASSWORD = By.xpath(".//p[text()='Некорректный пароль']");

    //локатор ссылки "Войти"
    private static final By LINK_ENTER = By.xpath(".//p[text()='Уже зарегистрированы?']/a[text()='Войти']");

    public static String password = "abc123abc";


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // <-- 2. Инициализируем wait
    }

    @Step("Заполнение поля Имя на странице регистрации")
    public void writeName(String name) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(NAME_FIELD));
        element.clear();
        element.sendKeys(name);
    }

    @Step("Заполнение поля Email на странице регистрации")
    public void writeEmail(String email) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(EMAIL_FIELD));
        element.clear();
        element.sendKeys(email);
    }

    @Step("Заполнение поля Пароль на странице регистрации")
    public void writePassword(String password) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(PASSWORD_FIELD));
        element.clear();
        element.sendKeys(password);
    }

    @Step("Клик по кнопке «Зарегистрироваться» на странице регистрации и переход на главную страницу")
    public MainPage clickButtonRegisterSuccess() {
        wait.until(ExpectedConditions.elementToBeClickable(BUTTON_REGISTER)).click();
        return new MainPage(driver);
    }

    @Step("Клик по кнопке «Зарегистрироваться» на странице регистрации и получение ошибки")
    public String clickButtonRegisterError() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(BUTTON_REGISTER));
        button.click();

        WebElement errorElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(ERROR_INVALID_PASSWORD)
        );
        return errorElement.getText().trim();
    }

    @Step("Клик на ссылку «Войти» на странице регистрации")
    public AuthorizationPage clickLinkEnter(){
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(LINK_ENTER));
        link.click();
        return new AuthorizationPage(driver);
    }
}

