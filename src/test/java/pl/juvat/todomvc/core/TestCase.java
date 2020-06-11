package pl.juvat.todomvc.core;

import io.qameta.allure.Step;
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

}
