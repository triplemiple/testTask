package elements;

import elements.interfaces.ElementInt;
import org.openqa.selenium.WebElement;

public abstract class AbstractElement implements ElementInt {
    protected final WebElement initialElement;

    protected AbstractElement(final WebElement wrappedElement) {
        this.initialElement = wrappedElement;
    }

    @Override
    public boolean isDisplayed() {
        return initialElement.isDisplayed();
    }
}
