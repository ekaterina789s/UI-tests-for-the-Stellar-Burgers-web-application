import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import page.MainPage;

@RunWith(Parameterized.class)
public class ConstructorParameterizedTest extends BaseUITest {

    private final String sectionName;

    public ConstructorParameterizedTest(String sectionName) {
        this.sectionName = sectionName;
    }

    @Parameterized.Parameters(name = "Проверка раздела: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                { "Булки"},
                { "Соусы"},
                { "Начинки"}
        });
    }

    @Test
    @DisplayName("Переход к разделу: {0}")
    @Description("Происходит переход к разделу {0} и проверяется видимость элементов")
    public void goSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.goToSection(sectionName);
        boolean isVisible = mainPage.isSectionElementsVisible(sectionName);
        Assert.assertTrue(
                "Элементы раздела '" + sectionName + "' не отображаются после перехода",
                isVisible
                );
    }
}
