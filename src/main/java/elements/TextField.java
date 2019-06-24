package elements;

import elements.interfaces.HasDefaultText;
import elements.interfaces.HasText;
import elements.interfaces.TextEditable;
import org.openqa.selenium.WebElement;

public class TextField extends AbstractElement implements TextEditable, HasText, HasDefaultText {

    public TextField(WebElement element){
        super(element);
    }

    @Override
    public void clear() {
        initialElement.clear();
    }

    @Override
    public void setText(String text) {
        initialElement.clear();
        initialElement.sendKeys(text);
    }

    @Override
    public String getText() {
        String text = initialElement.getAttribute("value");
        return (text != null && !text.isEmpty()) ? text : initialElement.getText();
    }

    @Override
    public String getDefaultText() {
        return initialElement.getAttribute("placeholder");
    }
}
