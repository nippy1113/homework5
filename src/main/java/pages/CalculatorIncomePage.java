package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utils;

import java.util.List;

public class CalculatorIncomePage extends BasePage {

    @FindBy(xpath = "//h1")
    private WebElement title;

    @FindBy(xpath = "//span[@class='calculator__currency-field-text']")
    private List<WebElement> currencyList;

    @FindBy(xpath = "//div[@class='calculator__slide-section']")
    private List<WebElement> slideSectionList;

    @FindBy(xpath = "//div[@class='jq-checkbox calculator__check']")
    private List<WebElement> checkBoxList;

    @FindBy(xpath = "//span[@class='js-calc-earned']")
    private WebElement earnedProcentInfo;

    @FindBy(xpath = "//span[@class='js-calc-replenish']")
    private WebElement fulfillForSixMonthInfo;

    @FindBy(xpath = "//span[@class='js-calc-result']")
    private WebElement resultInfo;

    @Step("Проверяем открытие страницы с заголовком '{title.getText()}'")
    public CalculatorIncomePage checkOpenPage() {
        Assertions.assertEquals("Вклады", title.getText().trim(), "Заголовок отсутствует на странице!");
        return this;
    }

    @Step("Выбираем валюту '{currencyName}'")
    public CalculatorIncomePage selectCurrency(String currencyName) {
        for (WebElement currencyItem : currencyList) {
            if (currencyItem.getText().trim().equals(currencyName)) {
                waitUtilElementToBeClickable(currencyItem).click();
                return this;
            }
        }
        Assertions.fail("Подменю '" + currencyName + "' не было найдено на стартовой странице!");
        return this;
    }

    @Step("Заполняем поле '{fieldName}' значением '{value}'")
    public CalculatorIncomePage fillField(String fieldName, String value) {
        for (WebElement inputItem : slideSectionList) {
            WebElement element = inputItem.findElement(By.xpath("./div/label"));
            if (element.getText().trim().equals(fieldName)) {
                List<WebElement> rangeScaleList = inputItem.findElements(By.xpath(".//div[contains(@class,'range-scale__item')]"));
                for (WebElement rangeScaleItem : rangeScaleList) {
                    if (rangeScaleItem.getText().trim().contains(value)) {
                        waitUtilElementToBeClickable(rangeScaleItem).click();
                        return this;
                    }
                }
            }
        }
        Assertions.fail("Поле '" + fieldName + "' не было найдено на странице!");
        return this;
    }

    @Step("Кликаем на '{checkBoxName}'")
    public CalculatorIncomePage clickOnCheckBox(String checkBoxName) {
        for (WebElement checkBoxItem : checkBoxList) {
            WebElement element = checkBoxItem.findElement(By.xpath("./../..//span/span"));
            if (element.getText().trim().equals(checkBoxName)) {
                waitUtilElementToBeClickable(element).click();
                return this;
            }
        }
        Assertions.fail("Поля '" + checkBoxName + "' не было найдено на стартовой странице!");
        return this;
    }

    @Step("Заполняем поля значениями : '{earnedProcent}' , '{fulfillForSixMonth}', '{result}'")
    public CalculatorIncomePage checkFinalFields(String earnedProcent, String fulfillForSixMonth, String result) {
        Assertions.assertEquals(earnedProcent, Utils.convertAmount(earnedProcentInfo.getText().trim()), "Результат поля ожидаеммый процент не соответствует ожидаемому!");
        Assertions.assertEquals(fulfillForSixMonth, Utils.convertAmount(fulfillForSixMonthInfo.getText().trim()), "Результат поля пополнение за 6 месяцев не соответствует ожидаемому!");
        Assertions.assertEquals(result, Utils.convertAmount(resultInfo.getText()), "Результат поля с итоовой суммой снятия за 6 месяцев не соответствует ожидаемому!");

        return this;
    }

    @Step("Ждем '{miliSeconds}' милисекунд")
    public CalculatorIncomePage waitForIt(int miliSeconds) throws InterruptedException {
        Thread.sleep(miliSeconds);
        return this;
    }


}
