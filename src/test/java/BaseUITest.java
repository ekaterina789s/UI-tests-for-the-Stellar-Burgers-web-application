import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseUITest {

    protected WebDriver driver;

    // ================= МЕНЯТЬ ТОЛЬКО ЗДЕСЬ =================
    // Поставьте "chrome" для обычного Chrome, "yandex" для Яндекс Браузера
    private static final String TARGET_BROWSER = "yandex";
    // =======================================================

    @Before
    public void startBrowser() {
        ChromeOptions options = new ChromeOptions();

        if ("chrome".equalsIgnoreCase(TARGET_BROWSER)) {
            System.out.println("Запуск обычного Chrome...");
            // Для Chrome ничего дополнительно указывать не нужно — Selenium сам его найдёт
        } else if ("yandex".equalsIgnoreCase(TARGET_BROWSER)) {
            System.out.println("Запуск Яндекс Браузера...");
            options.setBinary("C:\\Program Files (x86)\\Yandex\\YandexBrowser\\Application\\browser.exe");
        } else {
            throw new IllegalArgumentException("Неверный TARGET_BROWSER: " + TARGET_BROWSER + ". Допустимы: chrome, yandex");
        }

        // Автоматически подтягивает драйвер под версию браузера
        WebDriverManager.chromedriver().browserVersion("148").setup();

        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.education-services.ru/");

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


