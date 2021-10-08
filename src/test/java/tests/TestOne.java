package tests;

import basetestclass.BaseTest;
import org.junit.jupiter.api.Test;

public class TestOne extends BaseTest {

    @Test
    public void rubbleTest() throws InterruptedException {
        pageManager.getHomePage()
                .selectBaseMenu("Вклады")
                .selectSubMenu("Калькулятор доходности")
                .selectCurrency("Рубли")
                .fillField("Сумма вклада","500 т")
                .fillField("На срок","6")
                .fillField("Ежемесячное пополнение","1 млн")
                .clickOnCheckBox("Ежемесячная капитализация")
                .waitForIt(700)
                .checkFinalFields("89939,58", "5000000", "5589939,58");
    }

    @Test
    public void dollarTest() throws InterruptedException {
        pageManager.getHomePage()
                .selectBaseMenu("Вклады")
                .selectSubMenu("Калькулятор доходности")
                .selectCurrency("Доллары США")
                .fillField("Сумма вклада","5 т")
                .fillField("На срок","12")
                .fillField("Ежемесячное пополнение","5 т")
                .clickOnCheckBox("Ежемесячная капитализация")
                .waitForIt(700)
                .checkFinalFields("49,04", "55000", "60049,05");
    }
}
