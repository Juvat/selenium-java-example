package pl.juvat.todomvc.web.components;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.juvat.todomvc.web.WebComponent;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

/**
 * @author Kamil on 11.06.2020
 */
public final class TodoRow extends WebComponent {

    private final WebElement parent;

    @FindBy(css = ".toggle")
    private WebElement toggleButton;

    @FindBy(css = "label")
    private WebElement label;

    @FindBy(css = ".edit")
    private WebElement editInput;

    @FindBy(css = ".destroy")
    private WebElement deleteButton;

    TodoRow(final WebDriver driver, final WebElement parent) {
        super(driver, parent);
        this.parent = parent;
    }

    @Step
    public void toggleTodo() {
        toggleButton.click();
    }

    @Step
    public String getTodoText() {
        return label.getText();
    }

    @Step
    public void editTodo(final String todo) {
        doubleClickTodo();
        editInput.sendKeys(Keys.CONTROL + "A" + Keys.BACK_SPACE);
        editInput.sendKeys(todo + Keys.ENTER);
    }

    @Step
    public boolean isEditInputDisplayed() {
        return editInput.isDisplayed();
    }

    @Step
    public void deleteTodo() {
        actions.moveToElement(label).perform();
        wait.until(visibilityOf(deleteButton)).click();
    }

    @Step
    public boolean isTodoComplete() {
        return parent.getAttribute("class").contains("complete");
    }

    @Step
    public void doubleClickTodo() {
        actions.doubleClick(label).perform();
    }

}
