package pl.juvat.todomvc.tests;

import io.qameta.allure.Owner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.juvat.todomvc.core.TestCase;
import pl.juvat.todomvc.web.pages.TodoMvcPage;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Kamil on 11.06.2020
 */
@Owner("Kamil Lominski")
public class CompleteTodoTest extends TestCase {

    private TodoMvcPage todoMvcPage;

    @BeforeMethod
    public void prepareTest() {
        todoMvcPage = openTodoMvcPage();
        todoMvcPage.enterTodo("One");
    }

    @Test
    public void shouldMarkTodoAsComplete() {
        final var todoRow = todoMvcPage.getTodoList().getTodoRows().get(0);
        todoRow.toggleTodo();
        assertThat(todoRow.isTodoComplete()).isTrue();
        assertThat(todoMvcPage.getTodoCountText()).isEqualTo("0 items left");
    }

}
