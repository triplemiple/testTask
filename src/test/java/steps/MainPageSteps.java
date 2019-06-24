package steps;

import com.google.common.collect.ImmutableList;

import io.qameta.allure.Step;

import page.MainPage;
import utils.CustomLogger;

import java.util.List;
import java.util.stream.Collectors;

public class MainPageSteps extends AbstractSteps {

    private List<String> options = ImmutableList.of("Кол-во...", "25", "50", "100");

    @Step("Проверка элементов на главной странице")
    public MainPageSteps checkMainPageElementsVisibility() {

        MainPage mainPage = new MainPage();

        CustomLogger.equals("Название приложения", mainPage.getApplicationNameLbl().isDisplayed(),
                CustomLogger.DISPLAYED);

        CustomLogger.equals("Описание приложения", mainPage.getApplicationDescLbl().isDisplayed(),
                CustomLogger.DISPLAYED);

        CustomLogger.equals("Логотип", mainPage.getLogoImg().isDisplayed(),
                CustomLogger.DISPLAYED);

        CustomLogger.equals("Посмотреть все запросы", mainPage.getShowAllBtn().isDisplayed(),
                CustomLogger.DISPLAYED);

        CustomLogger.equals("Submit", mainPage.getSubmitBtn().isDisplayed(),
                CustomLogger.DISPLAYED);

        CustomLogger.equals("Введите ключевое слово", mainPage.getEnterValueTxtFld().isDisplayed(),
                CustomLogger.DISPLAYED);

        CustomLogger.equals("Количество", mainPage.getAmountDrpDwn().isDisplayed(),
                CustomLogger.DISPLAYED);

        CustomLogger.equals("Текст элемента 'Название приложения'", mainPage.getApplicationNameLbl().getText(),
                "StackOverSearch");

        CustomLogger.equals("Текст элемента 'Описание приложения'", mainPage.getApplicationDescLbl().getText(),
                "Быстрый поиск по StackExchange API");

        CustomLogger.equals("Текст элемента 'Посмотреть все запросы'", mainPage.getShowAllBtn().getText(),
                "Посмотреть все запросы");

        CustomLogger.equals("Текст элемента 'Submit'", mainPage.getSubmitBtn().getText(),
                "Search");

        CustomLogger.equals("Введите ключевое слово", mainPage.getEnterValueTxtFld().getDefaultText(),
               "Введите ключевое слово");

        List<String> values = mainPage.getAmountDrpDwn().getAllValues();

        if (!(values.size() == options.size()) || !values.containsAll(options)) {
            String checkedListOptions = values.stream().collect(Collectors.joining(", ", "'", "'"));
            String expectedListOptions = options.stream().collect(Collectors.joining(", ", "'", "'"));

            CustomLogger.warn(String.format("Ожидаемое значения списка \"Количество\" не совпадает с фактическим, ожидалось - %s, фактическое значение - %s",
                    expectedListOptions, checkedListOptions));
        }

        return this;
    }

    @Step("Выбираем количество отображаемых результатов - {0}")
    public MainPageSteps selectResultAmount(String value) {
        MainPage mainPage = new MainPage();
        mainPage.getAmountDrpDwn().select(value);
        return this;
    }

    @Step("Вводим запрос для поиска - {0}")
    public MainPageSteps typeQuery(String value) {
        MainPage mainPage = new MainPage();
        mainPage.getEnterValueTxtFld().setText(value);
        return this;
    }

    @Step("Проверить, что текст предупреждения равен - {0}")
    public MainPageSteps checklAlertText(String text) {
        MainPage mainPage = new MainPage();
        CustomLogger.equals("Текст предупреждения", mainPage.getAlertLbl().getText(), text);
        return this;
    }

    @Step("Нажимаем кнопку \"Search\"")
    public MainPageSteps clickSearch() {
        MainPage mainPage = new MainPage();
        mainPage.getSubmitBtn().click();
        return this;
    }

    @Step("Нажимаем кнопку \"Посмотреть все запросы\"")
    public MainPageSteps clickShowAll() {
        MainPage mainPage = new MainPage();
        mainPage.getShowAllBtn().click();
        return this;
    }
}
