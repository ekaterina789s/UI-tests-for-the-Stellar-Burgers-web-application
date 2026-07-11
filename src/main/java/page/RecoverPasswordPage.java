package page;

import io.qameta.allure.Step;
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
    private static final By LINK_ENTER_RECOVER_PAS = By.xpath(".//p[text()='Вспомнили пароль?']/a[text()='Войти']");

    @Step("Клик на ссылку «Войти» в форме восстановления пароля")
    public AuthorizationPage clickLinkEnterRecoverPas() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(LINK_ENTER_RECOVER_PAS));
        link.click();
        return new AuthorizationPage(driver);
    }
}
