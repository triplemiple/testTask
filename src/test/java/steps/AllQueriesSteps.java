package steps;

import config.DriverManager;
import io.qameta.allure.Step;
import page.ShowAllQueriesPage;
import utils.CustomLogger;
import utils.DateTimeUtils;

import java.util.List;
import java.util.Map;

public class AllQueriesSteps extends AbstractSteps {

    @Step("Проверка элементов на странице всех запросов")
    public AllQueriesSteps checkShowAllQueriesPageElementsVisibility() {

        ShowAllQueriesPage showAllQueriesPage = new ShowAllQueriesPage();

        CustomLogger.equals("Название страницы", showAllQueriesPage.getApplicationNameLbl().isDisplayed(),
                CustomLogger.DISPLAYED);

        CustomLogger.equals("Описание страницы", showAllQueriesPage.getPageDescLbl().isDisplayed(),
                CustomLogger.DISPLAYED);

        CustomLogger.equals("Логотип", showAllQueriesPage.getLogoImg().isDisplayed(),
                CustomLogger.DISPLAYED);

        CustomLogger.equals("Вернуться назад", showAllQueriesPage.getHeadBackBtn().isDisplayed(),
                CustomLogger.DISPLAYED);

        CustomLogger.equals("Сортировать по дате", showAllQueriesPage.getSortByDateBtn().isDisplayed(),
                CustomLogger.DISPLAYED);

        CustomLogger.equals("Не сортировать", showAllQueriesPage.getNotSortByDateBtn().isDisplayed(),
                CustomLogger.DISPLAYED);

        CustomLogger.equals("Таблица", showAllQueriesPage.getTable().isDisplayed(),
                CustomLogger.DISPLAYED);

        CustomLogger.equals("Текст элемента 'Название страницы'", showAllQueriesPage.getApplicationNameLbl().getText(),
                "Таблица всех запросов:");

        CustomLogger.equals("Текст элемента 'Описание страницы'", showAllQueriesPage.getPageDescLbl().getText(),
                "Что, во сколько.");

        CustomLogger.equals("Текст элемента 'Сортировать по дате'", showAllQueriesPage.getSortByDateBtn().getText(),
                "Сортировать по дате");

        CustomLogger.equals("Текст элемента 'Не сортировать'", showAllQueriesPage.getNotSortByDateBtn().getText(),
                "Не сортировать");

        CustomLogger.equals("Текст элемента 'Вернуться назад'", showAllQueriesPage.getHeadBackBtn().getText(),
                "Вернуться назад");

        return this;
    }

    @Step("Проверка формата столбцов {0} таблицы на странице всех запросов")
    public AllQueriesSteps checkTableFormat(int rowsCount) {
        ShowAllQueriesPage showAllQueriesPage = new ShowAllQueriesPage();
        List<Map<String, String>> rows = showAllQueriesPage.getTable().getRows(rowsCount);
        List<String> header = showAllQueriesPage.getTable().getTopRow();

        if (rows.isEmpty()) {
            CustomLogger.fail("Таблица с отображением всех запросов пуста");
        }

        for (int i = 0; i < rows.size(); i++) {
            Map<String, String> row = rows.get(i);
            for (String cellName : header) {
                if (row.get(cellName).isEmpty()) {
                    CustomLogger.warn(String.format("Значение в %d строке '%s' пустое", i + 1, cellName));
                }
            }
        }

        for (Map<String, String> row : rows) {
            String rowValue = row.get("Когда был запрос");
            if (!DateTimeUtils.isValidFormat(DateTimeUtils.DEFAULT_FORMAT, rowValue)) {
                CustomLogger.warn(String.format("Дата запроса не соответствует формату %s", DateTimeUtils.DEFAULT_FORMAT));
            }
        }
        return this;
    }

    @Step("Проверка сортировки {0} таблицы на странице всех запросов")
    public AllQueriesSteps checkDateSort(int rowsCount) {
        ShowAllQueriesPage showAllQueriesPage = new ShowAllQueriesPage();

        //Таблица сломана
        DriverManager.get().switchTo().alert().dismiss();

        List<Map<String, String>> rows = showAllQueriesPage.getTable().getRows(rowsCount);

        if (rows.isEmpty()) {
            CustomLogger.fail("Таблица с отображением всех запросов пуста");
        }

        for (int i = 0; i < rows.size() - 1; i++) {
            String firstDateVal = rows.get(i).get("Когда был запрос");
            String secondDateVal = rows.get(i + 1).get("Когда был запрос");
            if (!DateTimeUtils.isBefore(DateTimeUtils.DEFAULT_FORMAT, firstDateVal, secondDateVal)) {
                CustomLogger.warn(String.format("Дата запроса в строке %d меньше даты запроса в строке %d", i + 1, i + 2));
            }
        }
        return this;
    }

    @Step("Нажимаем на кнопку Сортировать по дате на странице всех запросов")
    public AllQueriesSteps clickSortBtn() {
        ShowAllQueriesPage showAllQueriesPage = new ShowAllQueriesPage();
        showAllQueriesPage.getSortByDateBtn().click();
        //Таблица сломана
        DriverManager.get().switchTo().alert().dismiss();
        return this;
    }

    @Step("Проверить, что значение в строке {0} столбца {1} таблицы Всех запросов соответствует значению {2}")
    public AllQueriesSteps checkValue(int rowNumber, String row, String value) {
        ShowAllQueriesPage showAllQueriesPage = new ShowAllQueriesPage();
        Map<String, String> rowData = showAllQueriesPage.getTable().getRow(rowNumber - 1);
        CustomLogger.equals(String.format("Значение в строке %d столбца %s", rowNumber, row), rowData.get(row), value);
        return this;
    }
}
