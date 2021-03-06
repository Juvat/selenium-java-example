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
public class EditTodoTest extends TestCase {

    private TodoMvcPage todoMvcPage;

    @BeforeMethod
    public void prepareTest() {
        todoMvcPage = openTodoMvcPage();
        todoMvcPage.enterTodo("One");
    }

    @Test
    public void doubleClickShouldEnableEdit() {
        final var todoRow = todoMvcPage.getTodoList().getTodoRows().get(0);
        assertThat(todoRow.isEditInputDisplayed()).isFalse();

        todoRow.doubleClickTodo();
        assertThat(todoRow.isEditInputDisplayed()).isTrue();
    }

    @Test
    public void shouldEditTodoItem() {
        final var todoRow = todoMvcPage.getTodoList().getTodoRows().get(0);
        todoRow.editTodo("Edited todo");
        assertThat(todoMvcPage.getTodoList().getTodoRows()).hasSize(1)
                                                           .extractingResultOf("getTodoText")
                                                           .containsOnly("Edited todo");
    }

    @Test
    public void emptyTextShouldRemoveTodoItem() {
        final var todoRow = todoMvcPage.getTodoList().getTodoRows().get(0);
        todoRow.editTodo("");
        assertThat(todoMvcPage.isTodoListDisplayed()).isFalse();
    }

    @AfterMethod
    public void clearLocalStorageAfterTest() {
        clearLocalStorage();
    }

}
