package elements;

import elements.interfaces.Selector;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.LinkedList;
import java.util.List;

public class DropDown extends AbstractElement implements Selector {

    Select select;

    public DropDown(WebElement element){
        super(element);
        this.select = new Select(initialElement);
    }

    @Override
    public void select(String value) {
        select.selectByValue(value);
    }

    @Override
    public List<String> getAllValues() {
        List<WebElement> options = select.getOptions();
        List<String> optionsText = new LinkedList<>();

        for (WebElement option: options) {
            optionsText.add(option.getText());
        }

        return optionsText;
    }
}
