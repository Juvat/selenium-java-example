package pl.juvat.todomvc.web.components;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.juvat.todomvc.web.WebComponent;

/**
 * @author Kamil on 11.06.2020
 */
public final class Footer extends WebComponent {

    @FindBy(css = ".todo-count")
    private WebElement todoCount;

    Footer(final WebDriver driver, final WebElement parent) {
        super(driver, parent);
    }

    @Step
    public String getTodoCountText() {
        return todoCount.getText();
    }

}
