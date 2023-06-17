package com.test.todo.testcases;

import com.test.todo.api.RegisterApi;
import com.test.todo.api.TasksApi;
import com.test.todo.base.BaseTest;
import com.test.todo.pages.NewTodoPage;
import com.test.todo.pages.TodoPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("todo feature")
public class TodoTest extends BaseTest {
    @Story("Add Todo")
    @Test(description = "should be able to add new todo correctly")
    public void shouldBeAbleToAddNewTodo() {
        RegisterApi registerApi = new RegisterApi();
        registerApi.register();

        NewTodoPage newTodoPage = new NewTodoPage(getDriver());
        newTodoPage.load();

        injectCookiesToBrowser(registerApi.getRestAssuredCookies());

        String actualResult = newTodoPage
                .load()
                .addNewTask("Cucumber")
                .getTodoText();

        Assert.assertEquals(actualResult, "Cucumber");
    }

    @Story("Delete story")
    @Test(description = "Should be able to delete todo correctly")
    public void shouldBeAbleToDeleteTodo() {
        RegisterApi registerApi = new RegisterApi();
        registerApi.register();

        TasksApi tasksApi = new TasksApi();
        tasksApi.addTask(registerApi.getAccessToken());

        TodoPage todoPage = new TodoPage(getDriver());
        todoPage.load();
        injectCookiesToBrowser(registerApi.getRestAssuredCookies());

        String actualResult = todoPage
                .load()
                .clickOnDeleteButton()
                .getNoTodoAvailable();

        Assert.assertEquals(actualResult, "No Available Todos");
    }
}
