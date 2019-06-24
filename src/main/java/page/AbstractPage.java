package page;

import config.DriverManager;
import config.ElementDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {

    protected WebDriver driver = DriverManager.get();

    public AbstractPage() {
        PageFactory.initElements(new ElementDecorator(driver), this);
    }
}
