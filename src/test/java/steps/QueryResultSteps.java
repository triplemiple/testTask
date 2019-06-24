package steps;

import io.qameta.allure.Step;
import page.QueryResultPage;
import utils.CustomLogger;
import utils.DateTimeUtils;

import java.util.List;
import java.util.Map;

public class QueryResultSteps extends AbstractSteps {

    @Step("Проверка элементов на странице результат запроса")
    public QueryResultSteps checkMainPageElementsVisibility() {

        QueryResultPage queryResultPage = new QueryResultPage();

        CustomLogger.equals("Название страницы", queryResultPage.getApplicationNameLbl().isDisplayed(),
                CustomLogger.DISPLAYED);

        CustomLogger.equals("Описание страницы", queryResultPage.getPageDescLbl().isDisplayed(),
                CustomLogger.DISPLAYED);

        CustomLogger.equals("Логотип", queryResultPage.getLogoImg().isDisplayed(),
                CustomLogger.DISPLAYED);

        CustomLogger.equals("Номер текущей страницы", queryResultPage.getPageNumber().isDisplayed(),
                CustomLogger.DISPLAYED);

        CustomLogger.equals("Вернуться назад", queryResultPage.getHeadBackBtn().isDisplayed(),
                CustomLogger.DISPLAYED);

        CustomLogger.equals("Таблица", queryResultPage.getTable().isDisplayed(),
                CustomLogger.DISPLAYED);


        CustomLogger.equals("Текст элемента 'Название страницы'", queryResultPage.getApplicationNameLbl().getText(),
                "Результаты запроса:");

        CustomLogger.equals("Текст элемента 'Описание приложения'", queryResultPage.getPageDescLbl().getText(),
                "Ну что же, приступим.");

        CustomLogger.equals("Текст элемента 'Номер текущей страницы'", queryResultPage.getPageNumber().getText(),
                "Страница 1");

        return this;
    }

    @Step("Проверка форматирования таблицы на странице результат запроса")
    public QueryResultSteps verifyQueryResultTable(int count) {

        QueryResultPage queryResultPage = new QueryResultPage();

        List<Map<String, String>> rows = queryResultPage.getTable().getAllRows();
        List<String> header = queryResultPage.getTable().getTopRow();

        if (rows.isEmpty()) {
            CustomLogger.fail("Таблица с отображением результата запроса пуста");
        }

        if (rows.size() != count) {
            CustomLogger.warn("Число результатов запроса не совпадает с запрошенным количеством");
        }

        for (int i = 0; i < rows.size(); i++) {
            Map<String, String> row = rows.get(i);
            for (String cellName : header) {
                if (row.get(cellName).isEmpty()) {
                    CustomLogger.warn(String.format("Значение в %d строке '%s' пустое", i + 1, cellName));
                }
            }
        }

        for (int i = 0; i < rows.size(); i++) {
            Map<String, String> row = rows.get(i);
            String rowValue = row.get("Создан");
            if (!DateTimeUtils.isValidFormat(DateTimeUtils.DEFAULT_FORMAT, rowValue)) {
                CustomLogger.warn(String.format("Дата запроса строки %d не соответствует формату %s", i + 1, DateTimeUtils.DEFAULT_FORMAT));
            }

            rowValue = row.get("Последняя активность");
            if (!DateTimeUtils.isValidFormat(DateTimeUtils.DEFAULT_FORMAT, rowValue)) {
                CustomLogger.warn(String.format("Дата запроса строки %d не соответствует формату %s", i + 1, DateTimeUtils.DEFAULT_FORMAT));
            }

            rowValue = row.get("#");
            if (Integer.parseInt(rowValue) != i + 1) {
                CustomLogger.warn(String.format("Порядковый номер в строке %d имеет неверное значение - %s, ожидалось - %d",
                        i + 1, rowValue, i + 1));
            }
        }
        return this;
    }

    @Step("Нажимаем на кнопку Вернуться назад")
    public QueryResultSteps pressGoBackBtn() {
        QueryResultPage queryResultPage = new QueryResultPage();
        queryResultPage.getHeadBackBtn().click();
        return this;
    }
}
