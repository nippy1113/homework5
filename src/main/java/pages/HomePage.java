package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[contains(@class, 'header__content-bottom-nav-link nav__link nav__link_parent')]")
    private List<WebElement> listMainMenu;

    @FindBy(xpath = "//li//li/a[@class = 'nav__link']")
    private List<WebElement> listSubMenu;



    /**
     * Функция наведения мыши на любой пункт меню
     *
     * @param nameBaseMenu - наименование меню
     *
     */
    @Step("Кликаем на меню '{nameBaseMenu}'")
    public HomePage selectBaseMenu(String nameBaseMenu) {
        for (WebElement menuItem : listMainMenu) {
            WebElement element = menuItem.findElement(By.xpath("./span"));
            if (element.getText().trim().contains(nameBaseMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                Assertions.assertTrue(menuItem.findElement(By.xpath("./..//div[@class='nav__item-sub-nav-title']"))
                                                .getText().trim().toLowerCase().contains(nameBaseMenu.toLowerCase()),
                        "Заголовок отсутствует/не соответствует требуемому");

                return this;
            }
        }
        Assertions.fail("Меню '" + nameBaseMenu + "' не было найдено на стартовой странице!");
        return this;
    }

    @Step("Кликаем на подменю '{nameSubMenu}'")
    public CalculatorIncomePage selectSubMenu(String nameSubMenu) {
        for (WebElement subMenuItem : listSubMenu) {
            if (subMenuItem.getText().trim().contains(nameSubMenu)) {
                waitUtilElementToBeClickable(subMenuItem).click();
                return pageManager.getCalculatorIncomePage().checkOpenPage();
            }
        }
        Assertions.fail("Подменю '" + nameSubMenu + "' не было найдено на стартовой странице!");
        return pageManager.getCalculatorIncomePage();
    }


}
