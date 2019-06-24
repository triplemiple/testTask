package page;

import config.DriverManager;
import elements.Button;
import elements.DropDown;
import elements.Image;
import elements.Label;
import elements.TextField;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AbstractPage {

    private String ALERT_XPATH = "//div[@class = 'alert alert-danger']";

    /**
     * Название приложения
     */
    @FindBy(xpath = "//*[@class='card-title']")
    private Label applicationNameLbl;

    /**
     * Описание приложения
     */
    @FindBy(xpath = "//*[@class='text-muted']")
    private Label applicationDescLbl;

    /**
     * Логотип
     */
    @FindBy(xpath = "//div[@class='card-block']//img")
    private Image logoImg;

    /**
     * Посмотреть все запросы
     */
    @FindBy(xpath = "//button[contains(@class, 'btn-sm')]")
    private Button showAllBtn;

    /**
     * Submit
     */
    @FindBy(xpath = "//input[@type = 'Submit']")
    private Button submitBtn;

    /**
     * Введите ключевое слово
     */
    @FindBy(xpath = "//input[@class = 'form-control']")
    private TextField enterValueTxtFld;

    /**
     * Количество
     */
    @FindBy(xpath = "//select[contains(@class, 'custom-select')]")
    private DropDown amountDrpDwn;

    public Image getLogoImg() {
        return logoImg;
    }

    public Button getShowAllBtn() {
        return showAllBtn;
    }

    public Button getSubmitBtn() {
        return submitBtn;
    }

    public TextField getEnterValueTxtFld() {
        return enterValueTxtFld;
    }

    public DropDown getAmountDrpDwn() {
        return amountDrpDwn;
    }

    public Label getApplicationNameLbl() {
        return applicationNameLbl;
    }

    public Label getApplicationDescLbl() {
        return applicationDescLbl;
    }

    public Label getAlertLbl() {
        return new Label(DriverManager.get().findElement(By.xpath(ALERT_XPATH)));
    }
}

