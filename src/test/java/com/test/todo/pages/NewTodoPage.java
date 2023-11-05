package com.test.todo.pages;

import com.test.todo.base.BasePage;
import com.test.todo.config.EndPoint;
import com.test.todo.utilies.ConfigUtils;
import io.qameta.allure.Step;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewTodoPage extends BasePage {
    private final By newToDo = By.cssSelector("[data-testid=\"new-todo\"]");
    private final By submitNewToDo = By.cssSelector("[data-testid=\"submit-newTask\"]");

    public NewTodoPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public NewTodoPage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.NEW_TODO_ENDPOINT);
        return this;
    }

    @Step
    public TodoPage addNewTask(String todoName) {
        driver.findElement(newToDo).sendKeys(todoName);
        driver.findElement(submitNewToDo).click();
        return new TodoPage(driver);
    }

}

