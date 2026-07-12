package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;

    private final WebDriverWait wait;

    //кнопка "Личный кабинет"
    private static final By BUTTON_PERSONAL_ACCOUNT = By.xpath("//p[text()='Личный Кабинет']");

    //кнопка "Войти в аккаунт"
    private static final By BUTTON_ENTER_ACCOUNT = By.xpath(".//button[text()='Войти в аккаунт']");

    //Надпись раздела "Булки"
    private static final By NAME_SECTION_BREAD = By.xpath("//h2[normalize-space()='Булки']");

    //элементы раздела "Булки"
    private static final By ELEMENTS_SECTION_BREAD = By.xpath("//h2[normalize-space()='Булки']/following-sibling::ul[1]");

    //Надпись раздела "Соусы"
    private static final By NAME_SECTION_SAUCES = By.xpath(".//div[@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Соусы']");

    //Элементы раздела "Соусы"
    private static final By ELEMENTS_SECTION_SAUCES = By.xpath("//h2[normalize-space()='Соусы']/following-sibling::ul[1]");

    //Надпись раздела "Начинки"
    private static final By NAME_SECTION_TOPPINGS = By.xpath(".//div[@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Начинки']");

    //Элементы раздела "Начинки"
    private static final By ELEMENTS_SECTION_TOPPINGS = By.xpath("//h2[normalize-space()='Начинки']/following-sibling::ul");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Клик по кнопке «Личный кабинет» на главной странице")
    public AuthorizationPage clickButtonPersonalAccount(){
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(BUTTON_PERSONAL_ACCOUNT));
        button.click();
        return new AuthorizationPage(driver);
    }

    @Step("Клик по кнопке «Войти в аккаунт» на главной странице")
    public AuthorizationPage clickButtonEnterAccount(){
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(BUTTON_ENTER_ACCOUNT));
        button.click();
        return new AuthorizationPage(driver);

    }

    @Step("Переход к разделу: {sectionName}")
    public void goToSection(String sectionName) {
        By headerLocator = getHeaderLocator(sectionName);
        if (headerLocator == null) {
            return;
        }
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(headerLocator));
        element.click();
    }

    @Step("Проверка видимости элементов раздела: {sectionName}")
    public boolean isSectionElementsVisible(String sectionName) {
        By listLocator = getListLocator(sectionName);
        if (listLocator == null) {
            return false;
        }
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(listLocator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Вспомогательный метод: получает локатор заголовка раздела по его названию.
    private By getHeaderLocator(String sectionName) {
        switch (sectionName) {
            case "Булки":
                return NAME_SECTION_BREAD;
            case "Соусы":
                return NAME_SECTION_SAUCES;
            case "Начинки":
                return NAME_SECTION_TOPPINGS;
            default:
                return null;
        }
    }

    //Вспомогательный метод: получает локатор списка элементов раздела по его названию.
    private By getListLocator(String sectionName) {
        switch (sectionName) {
            case "Булки":
                return ELEMENTS_SECTION_BREAD;
            case "Соусы":
                return ELEMENTS_SECTION_SAUCES;
            case "Начинки":
                return ELEMENTS_SECTION_TOPPINGS;
            default:
                return null;
        }
    }
}

