package test;

import config.DriverManager;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steps.AllQueriesSteps;
import steps.MainPageSteps;
import steps.QueryResultSteps;

import java.util.UUID;

public class SearchQuerySuite extends Hooks {

    @Test(testName = "4.1 Проверка отображения запроса в истории запросов")
    @Parameters({"amount"})
    public void test4_1(String amount) {
        final String uuid = UUID.randomUUID().toString();

        new MainPageSteps()
                .typeQuery(uuid)
                .selectResultAmount(amount)
                .clickSearch();

        new QueryResultSteps().pressGoBackBtn();

        new MainPageSteps().clickShowAll();

        //Таблица сломана
        DriverManager.get().switchTo().alert().dismiss();
        new AllQueriesSteps().clickSortBtn().checkValue(1,"Какой был запрос", uuid);
    }

}
