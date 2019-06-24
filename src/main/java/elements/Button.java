package elements;

import elements.interfaces.Clickable;
import elements.interfaces.HasText;
import org.openqa.selenium.WebElement;

public class Button extends AbstractElement implements Clickable, HasText {

    public Button(WebElement element){
        super(element);
    }

    @Override
    public void click() {
        initialElement.click();
    }

    @Override
    public String getText() {
        String text =  initialElement.getText();
        return (text != null && !text.isEmpty()) ? text : initialElement.getAttribute("value");
    }
}
