package page;

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
    public static final By NAME_SECTION_BREAD = By.xpath("//h2[normalize-space()='Булки']");

    //элементы раздела "Булки"
    public static final By ELEMENTS_SECTION_BREAD = By.xpath("//h2[normalize-space()='Булки']/following-sibling::ul[1]");

    //Надпись раздела "Соусы"
    public static final By NAME_SECTION_SAUCES = By.xpath(".//div[@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Соусы']");

    //Элементы раздела "Соусы"
    public static final By ELEMENTS_SECTION_SAUCES = By.xpath("//h2[normalize-space()='Соусы']/following-sibling::ul[1]");

    //Надпись раздела "Начинки"
    public static final By NAME_SECTION_TOPPINGS = By.xpath(".//div[@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Начинки']");

    //Элементы раздела "Начинки"
    public static final By ELEMENTS_SECTION_TOPPINGS = By.xpath("//h2[normalize-space()='Начинки']/following-sibling::ul");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //клик по кнопке "Личный кабинет"
    public AuthorizationPage clickButtonPersonalAccount(){
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(BUTTON_PERSONAL_ACCOUNT));
        button.click();
        return new AuthorizationPage(driver);
    }

    //клик по кнопке "Войти в аккаунт" на главной странице
    public AuthorizationPage clickButtonEnterAccount(){
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(BUTTON_ENTER_ACCOUNT));
        button.click();
        return new AuthorizationPage(driver);

    }

    // Универсальный метод клика по любому заголовку
    public void clickSectionGeneric(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    // Универсальный метод проверки видимости любого списка
    public void visibilityElementsGeneric(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
