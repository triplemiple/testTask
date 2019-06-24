package test;

import config.DriverManager;
import org.testng.annotations.Ignore;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steps.AllQueriesSteps;
import steps.MainPageSteps;

public class ShowAllQueriesSuite extends Hooks {

    @Test(testName = "2.1 Отображение основных элементов на странице всех запросов")
    public void test2_1() {
        new MainPageSteps().clickShowAll();
        //Таблица сломана
        DriverManager.get().switchTo().alert().dismiss();
        new AllQueriesSteps().checkShowAllQueriesPageElementsVisibility();
    }

    @Test(testName = "2.2 Проверка формата значений в таблице на странице всех запросов")
    @Parameters({"amount"})
    public void test2_2(int amount) {
        new MainPageSteps().clickShowAll();
        //Таблица сломана
        DriverManager.get().switchTo().alert().dismiss();
        //Таблица сломана
        new AllQueriesSteps().checkTableFormat(amount);
    }

    @Test(testName = "2.3 Проверка сортировки по дате на странице всех запросов")
    @Parameters({"amount"})
    public void test2_3(int amount) {
        new MainPageSteps().clickShowAll();
        //Таблица сломана
        DriverManager.get().switchTo().alert().dismiss();
        //Таблица сломана
        new AllQueriesSteps().clickSortBtn().checkDateSort(amount);
    }
}

