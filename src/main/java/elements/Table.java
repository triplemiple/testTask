package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Table extends AbstractElement {

    protected final String TOP_CELL_XPATH = "//th";
    protected final String CELL_XPATH = ".//td";
    protected final String ROW_XPATH = ".//tbody//tr";

    public Table(WebElement element) {
        super(element);
    }

    public List<String> getTopRow() {
        List<String> values = new LinkedList<>();

        List<WebElement> headerCells = initialElement.findElements(By.xpath(TOP_CELL_XPATH));

        for (WebElement cell : headerCells) {
            values.add(cell.getText());
        }

        return values;
    }

    public List<Map<String, String>> getAllRows() {
        List<WebElement> headerCells = initialElement.findElements(By.xpath(TOP_CELL_XPATH));

        List<WebElement> rows = initialElement.findElements(By.xpath(ROW_XPATH));
        List<Map<String, String>> cells = new LinkedList<>();

        for (WebElement row : rows) {
            Map<String, String> cellsValues = new HashMap<>();
            List<WebElement> rowData = row.findElements(By.xpath(CELL_XPATH));
            for (int i = 0; i < headerCells.size(); i++) {
                cellsValues.put(headerCells.get(i).getText(), rowData.get(i).getText());
            }
            cells.add(cellsValues);
        }

        return cells;
    }

    public List<Map<String, String>> getRows(int count) {
        List<WebElement> headerCells = initialElement.findElements(By.xpath(TOP_CELL_XPATH));

        List<WebElement> rows = initialElement.findElements(By.xpath(ROW_XPATH));
        List<Map<String, String>> cells = new LinkedList<>();

        int rowsCount = count >= rows.size() ? cells.size() : count;

        for (int i = 0; i < rowsCount; i++) {
            Map<String, String> cellsValues = new HashMap<>();
            List<WebElement> rowData = rows.get(i).findElements(By.xpath(CELL_XPATH));
            for (int j = 0; j < headerCells.size(); j++) {
                cellsValues.put(headerCells.get(j).getText(), rowData.get(j).getText());
            }
            cells.add(cellsValues);
        }

        return cells;
    }

    public Map<String, String> getRow(int rowNumber) {
        List<WebElement> headerCells = initialElement.findElements(By.xpath(TOP_CELL_XPATH));

        List<WebElement> rows = initialElement.findElements(By.xpath(ROW_XPATH));

        Map<String, String> cellsValues = new HashMap<>();
        List<WebElement> rowData = rows.get(rowNumber).findElements(By.xpath(CELL_XPATH));
        for (int i = 0; i < headerCells.size(); i++) {
            cellsValues.put(headerCells.get(i).getText(), rowData.get(i).getText());
        }
        return cellsValues;
    }
}

