package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthorizationPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public String email;
    public String password;

    public static final By LINK_REGISTER = By.xpath(".//p//a[normalize-space() = 'Зарегистрироваться']");
    public static final By EMAIL_AUTH = By.xpath("//div[label[normalize-space() = 'Email']]//input");
    public static final By PASSWORD_AUTH = By.xpath("//div[label[normalize-space() = 'Пароль']]//input[@type='password']");
    public static final By BUTTON_ENTER = By.xpath("//button[normalize-space() = 'Войти']");
    public static final By LINK_RECOVER_PASSWORD = By.xpath(".//a[text()='Восстановить пароль']");

    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //клик по ссылке "Зарегистрироваться"
    public RegistrationPage clickLinkRegister() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(LINK_REGISTER));
        element.click();
        return new RegistrationPage(driver);
    }

    // Заполнение полей Email и Пароль
    public void fillEmailAndPassword_AuthPage(String email, String password) {
        WebElement emailField = driver.findElement(EMAIL_AUTH);
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = driver.findElement(PASSWORD_AUTH);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    //Клик на кнопку "Войти"
    public MainPage clickButtonEnter() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(BUTTON_ENTER));
        button.click();
        return new MainPage(driver);
    }

    //Клик на ссылку "Восстановить пароль"
    public RecoverPasswordPage clickLinkRecoverPassword(){
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(LINK_RECOVER_PASSWORD));
        link.click();
        return new RecoverPasswordPage(driver);
    }
}

