package elements;

import elements.interfaces.HasText;
import org.openqa.selenium.WebElement;

public class Label extends AbstractElement implements HasText {

    public Label(WebElement element){
        super(element);
    }

    @Override
    public String getText() {
        return initialElement.getText();
    }
}
