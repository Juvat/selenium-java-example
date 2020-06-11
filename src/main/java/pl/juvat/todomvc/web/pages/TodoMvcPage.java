package pl.juvat.todomvc.web.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.juvat.todomvc.web.WebPage;
import pl.juvat.todomvc.web.components.TodoList;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

/**
 * @author Kamil on 11.06.2020
 */
public final class TodoMvcPage extends WebPage {

    @FindBy(css = ".new-todo")
    private WebElement todoInput;

    @FindBy(css = ".todo-list")
    private WebElement todoList;

    @FindBy(css = ".footer")
    private WebElement footer;

    @FindBy(css = ".todo-count")
    private WebElement todoCount;

    public TodoMvcPage(final WebDriver driver) {
        super(driver);
        wait.until(visibilityOf(todoInput));
    }

    @Step
    public void enterTodo(final String todo) {
        todoInput.sendKeys(todo + Keys.ENTER);
    }

    @Step
    public void enterMultipleTodos(final String... todos) {
        for (final var todo : todos) {
            todoInput.sendKeys(todo + Keys.ENTER);
        }
    }

    @Step
    public String getInputText() {
        return todoInput.getAttribute("value");
    }

    @Step
    public boolean isTodoListDisplayed() {
        return todoList.isDisplayed();
    }

    @Step
    public TodoList getTodoList() {
        return new TodoList(driver, todoList);
    }

    @Step
    public boolean isFooterDisplayed() {
        return footer.isDisplayed();
    }

    @Step
    public String getTodoCountText() {
        return todoCount.getText();
    }

}
