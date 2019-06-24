package page;

import config.DriverManager;
import elements.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class QueryResultPage extends AbstractPage {

    protected final static String BACK_BTN_XPATH = "//button[text() = 'Назад']";
    protected final static String FORWARD_BTN_XPATH = "//button[text() = 'Вперед']";

    /**
     * Название страницы
     */
    @FindBy(xpath = "//*[@class='card-title']")
    private Label applicationNameLbl;

    /**
     * Описание страницы
     */
    @FindBy(xpath = "//*[@class='text-muted']")
    private Label pageDescLbl;

    /**
     * Номер текущей страницы
     */
    @FindBy(xpath = "//h5//strong")
    private Label pageNumber;

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

    public Table getTable() {
        return table;
    }

    public Label getPageNumber() {
        return pageNumber;
    }

    public Button getBackBtn() {
        return new Button(DriverManager.get().findElement(By.xpath(BACK_BTN_XPATH)));
    }

    public Button getForwardBtn() {
        return new Button(DriverManager.get().findElement(By.xpath(FORWARD_BTN_XPATH)));
    }
}

