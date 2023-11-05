package com.test.todo.pages;

import com.test.todo.base.BasePage;
import com.test.todo.config.EndPoint;
import com.test.todo.utilies.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TodoPage extends BasePage {
    public TodoPage(WebDriver driver) {
        super(driver);
    }
    private final By welcomeMessage = By.cssSelector("[data-testid=\"welcome\"]");
    private final By addNewTodo = By.cssSelector("[data-testid=\"add\"]");
    private final By todoItem = By.cssSelector("[data-testid=\"todo-item\"]");
    private final By deleteButton = By.cssSelector("[data-testid=\"delete\"]");
    private final By noTodoAvailable = By.cssSelector("[data-testid=\"no-todos\"]");


    @Step
    public boolean isWelcomeMessageDisplayed() {
        return driver.findElement(welcomeMessage).isDisplayed();
    }

    @Step
    public NewTodoPage clickOnPlusButton() {
        driver.findElement(addNewTodo).click();
        return new NewTodoPage(driver);
    }

    @Step
    public String getTodoText() {
        return driver.findElement(todoItem).getText();
    }

    @Step
    public TodoPage clickOnDeleteButton() {
        driver.findElement(deleteButton).click();
        return this;
    }

    @Step
    public String getNoTodoAvailable() {
        return driver.findElement(noTodoAvailable).getText();
    }

    @Step
    public TodoPage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.TODO_PAGE_ENDPOINT);
        return this;
    }
}
