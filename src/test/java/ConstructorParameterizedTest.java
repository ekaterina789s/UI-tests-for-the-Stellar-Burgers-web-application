import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import org.openqa.selenium.By;
import page.MainPage;

@RunWith(Parameterized.class)
public class ConstructorParameterizedTest extends BaseUITest {

    // Поля, которые будут заполняться для каждого запуска теста
    private final String sectionName;
    private final By headerLocator;
    private final By listLocator;

    // Конструктор
    public ConstructorParameterizedTest(String sectionName, By headerLocator, By listLocator) {
        this.sectionName = sectionName;
        this.headerLocator = headerLocator;
        this.listLocator = listLocator;
    }

    // Метод, который возвращает коллекцию параметров (наборы данных)
    @Parameterized.Parameters(name = "Проверка раздела: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // { "Название для отчета", Локатор заголовка (клик), Локатор списка (проверка) }
                { "Булки", MainPage.NAME_SECTION_BREAD, MainPage.ELEMENTS_SECTION_BREAD },
                { "Соусы", MainPage.NAME_SECTION_SAUCES, MainPage.ELEMENTS_SECTION_SAUCES },
                { "Начинки", MainPage.NAME_SECTION_TOPPINGS, MainPage.ELEMENTS_SECTION_TOPPINGS }
        });
    }

    @Test
    @DisplayName("Переход к разделу: {0}")
    @Description("Происходит переход к разделу {0} и проверяется видимость элементов")
    public void goSectionTest() {
        MainPage mainPage = new MainPage(driver);

        // Клик по заголовку раздела
        mainPage.clickSectionGeneric(headerLocator);

        // Проверка видимости списка элементов раздела
        mainPage.visibilityElementsGeneric(listLocator);
    }
}
