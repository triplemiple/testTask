package test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steps.MainPageSteps;

public class MainPageSuite extends Hooks {

    @Test(testName = "1.1 Отображение основных элементов на главной странице")
    public void test1_1() {
       new MainPageSteps().checkMainPageElementsVisibility();
    }

    @Test(testName = "1.2.1 Проверка отображения предупреждения")
    @Parameters({"amount"})
    public void test1_2_1(String amount) {
        new MainPageSteps()
                .selectResultAmount(amount)
                .clickSearch()
                .checklAlertText("Не переданы обязательные параметры");
    }

    @Test(testName = "1.2.2 Проверка отображения предупреждения")
    @Parameters({"query"})
    public void test1_2_2(String query) {
        new MainPageSteps()
                .typeQuery(query)
                .clickSearch()
                .checklAlertText("Не переданы обязательные параметры");
    }
}
