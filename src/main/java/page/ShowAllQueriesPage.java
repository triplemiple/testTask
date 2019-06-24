package page;

import config.DriverManager;
import elements.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class ShowAllQueriesPage extends AbstractPage {

    /**
     * Название страницы
     */
    @FindBy(xpath = "//*[@class='card-title']")
    private Label applicationNameLbl;

    /**
     * Описание приложения
     */
    @FindBy(xpath = "//*[@class='text-muted']")
    private Label pageDescLbl;

    /**
     * Логотип
     */
    @FindBy(xpath = "//div[@class='card-block']//img")
    private Image logoImg;

    /**
     * Вернуться назад
     */
    @FindBy(xpath = "//button[contains(@class, 'btn-sm')]")
    private Button headBackBtn;

    /**
     * Сортировать по дате
     */
    @FindBy(xpath = "(//button[@class = 'btn btn-sm btn-block'])[1]")
    private Button sortByDateBtn;

    /**
     * Не сортировать
     */
    @FindBy(xpath = "(//button[@class = 'btn btn-sm btn-block'])[2]")
    private TextField notSortByDateBtn;

    /**
     * Таблица
     */
    @FindBy(xpath = "//table[@class = 'table table-hover table-striped']")
    private Table table;

    public Label getApplicationNameLbl() {
        return applicationNameLbl;
    }

    public Label getPageDescLbl() {
        return pageDescLbl;
    }

    public Image getLogoImg() {
        return logoImg;
    }

    public Button getHeadBackBtn() {
        return headBackBtn;
    }

    public Button getSortByDateBtn() {
        return sortByDateBtn;
    }

    public TextField getNotSortByDateBtn() {
        return notSortByDateBtn;
    }

    public Table getTable() {
        return table;
    }
}

