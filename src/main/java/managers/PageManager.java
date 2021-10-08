package managers;

import pages.*;

public class PageManager {

    private static BasePage basePage;

    /**
     * Менеджер страничек
     */
    private static PageManager pageManager;

    /**
     * Стартовая страничка
     */
    private HomePage homePage;

    /**
     * Страничка калькулятора доходности
     */
    private CalculatorIncomePage calculatorIncomePage;

    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Ленивая инициализация {@link HomePage}
     *
     * @return StartPage
     */
    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    /**
     * Ленивая инициализация {@link CalculatorIncomePage}
     *
     * @return StartPage
     */
    public CalculatorIncomePage getCalculatorIncomePage() {
        if (calculatorIncomePage == null) {
            calculatorIncomePage = new CalculatorIncomePage();
        }
        return calculatorIncomePage;
    }
}
