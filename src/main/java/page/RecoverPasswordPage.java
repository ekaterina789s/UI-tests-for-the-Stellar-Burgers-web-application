package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecoverPasswordPage {
    WebDriver driver;

    private final WebDriverWait wait;

    public RecoverPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Ссылка "Войти"
    public static final By LINK_ENTER_RECOVER_PAS = By.xpath(".//p[text()='Вспомнили пароль?']/a[text()='Войти']");

    //клик на ссылку "Войти"
    public AuthorizationPage clickLinkEnter_RecoverPas() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(LINK_ENTER_RECOVER_PAS));
        link.click();
        return new AuthorizationPage(driver);
    }
}
