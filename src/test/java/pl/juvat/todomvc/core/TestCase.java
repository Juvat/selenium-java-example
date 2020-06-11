package pl.juvat.todomvc.core;

import io.qameta.allure.Step;
import pl.juvat.todomvc.browser.BrowserLocalStorage;
import pl.juvat.todomvc.web.pages.TodoMvcPage;

/**
 * @author Kamil on 11.06.2020
 */
public abstract class TestCase extends TestCore {

    @Step
    public TodoMvcPage openTodoMvcPage() {
        driver.get(getBaseUrl());
        return new TodoMvcPage(driver);
    }

    @Step
    public void clearLocalStorage() {
        new BrowserLocalStorage(driver).clear();
    }

}
