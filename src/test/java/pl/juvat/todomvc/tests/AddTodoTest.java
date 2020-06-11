package pl.juvat.todomvc.tests;

import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.juvat.todomvc.core.TestCase;
import pl.juvat.todomvc.web.pages.TodoMvcPage;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Kamil on 11.06.2020
 */
@Owner("Kamil Lominski")
public class AddTodoTest extends TestCase {

    private static final String TODO_ONE = "One", TODO_TWO = "Two";
    private TodoMvcPage todoMvcPage;

    @BeforeMethod
    public void prepareTest() {
        todoMvcPage = openTodoMvcPage();
    }

    @Test
    public void shouldAddTodoItemsToList() {
        final var methodName = "getTodoText";

        todoMvcPage.enterTodo(TODO_ONE);
        var todoRows = todoMvcPage.getTodoList().getTodoRows();
        assertThat(todoRows).hasSize(1).extractingResultOf(methodName).containsOnly(TODO_ONE);

        todoMvcPage.enterTodo(TODO_TWO);
        todoRows = todoMvcPage.getTodoList().getTodoRows();
        assertThat(todoRows).hasSize(2).extractingResultOf(methodName).containsOnly(TODO_ONE, TODO_TWO);
    }

    @Test
    public void shouldShowFooterAndListAfterAddingTodoItem() {
        todoMvcPage.enterTodo(TODO_ONE);
        assertThat(todoMvcPage.isFooterDisplayed()).isTrue();
    }

    @Test
    public void shouldClearInputFieldWhenTodoItemIsAdded() {
        todoMvcPage.enterTodo(TODO_ONE);
        assertThat(todoMvcPage.getInputText()).isBlank();
    }

    @AfterMethod
    public void clearLocalStorageAfterTest() {
        clearLocalStorage();
    }

}
