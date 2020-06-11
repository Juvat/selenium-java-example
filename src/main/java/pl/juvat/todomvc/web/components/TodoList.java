package pl.juvat.todomvc.web.components;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.juvat.todomvc.web.WebComponent;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Kamil on 11.06.2020
 */
public final class TodoList extends WebComponent {

    @Getter
    private final List<TodoRow> todoRows;

    @FindBy(css = "li.todo")
    private List<WebElement> rows;

    public TodoList(final WebDriver driver, final WebElement parent) {
        super(driver, parent);
        todoRows = mapTodoRows();
    }

    private List<TodoRow> mapTodoRows() {
        return rows.stream().map(row -> new TodoRow(driver, row)).collect(toList());
    }

}
