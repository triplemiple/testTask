package test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steps.MainPageSteps;
import steps.QueryResultSteps;

public class QueryResultSuite extends Hooks {

    @DataProvider(name = "queryData")
    public Object[][] queryData() {
        return new Object[][]{
                {"SQL", "25"},
                {"SQL", "50"},
                {"SQL", "100"},
                {"50", "25"},
                {"50", "50"},
                {"50", "100"},
        };
    }

    @Test(testName = "3.1 Проверка отображения основных элементов на страце Результат запроса")
    @Parameters({"query", "amount"})
    public void test3_1(String query, String amount) {
        new MainPageSteps()
                .typeQuery(query)
                .selectResultAmount(amount)
                .clickSearch();

        new QueryResultSteps().checkMainPageElementsVisibility();
    }

    @Test(testName = "3.2 Проверка отображения основных элементов на страце Результат запроса", dataProvider = "queryData")
    public void test3_2(String query, String amount) {
        new MainPageSteps()
                .typeQuery(query)
                .selectResultAmount(amount)
                .clickSearch();

        new QueryResultSteps().verifyQueryResultTable(Integer.parseInt(amount));
    }
}
