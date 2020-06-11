package pl.juvat.todomvc.tests;

import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.juvat.todomvc.core.TestCase;
import pl.juvat.todomvc.web.pages.TodoMvcPage;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Kamil on 11.06.2020
 */
@Owner("Kamil Lominski")
public class DeleteTodoTest extends TestCase {

    private final static String TODO_ONE = "One", TODO_TWO = "Two";
    private TodoMvcPage todoMvcPage;

    @BeforeClass
    public void prepareTest() {
        todoMvcPage = openTodoMvcPage();
        todoMvcPage.enterMultipleTodos(TODO_ONE, TODO_TWO);
    }

    @Test
    public void shouldDeleteTodoItems() {
        var todoRows = todoMvcPage.getTodoList().getTodoRows();
        todoRows.get(1).deleteTodo();
        todoRows = todoMvcPage.getTodoList().getTodoRows();
        assertThat(todoRows).hasSize(1).extractingResultOf("getTodoText").containsOnly(TODO_ONE);

        todoRows.get(0).deleteTodo();
        assertThat(todoMvcPage.isTodoListDisplayed()).isFalse();
    }

}
