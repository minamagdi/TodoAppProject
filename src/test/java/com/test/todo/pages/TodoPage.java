package com.test.todo.pages;

import com.test.todo.base.BasePage;
import com.test.todo.config.EndPoint;
import com.test.todo.utilies.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TodoPage extends BasePage {
    public TodoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-testid=\"welcome\"]")
    private WebElement welcomeMessage;
    @FindBy(css = "[data-testid=\"add\"]")
    private WebElement addNewTodo;
    @FindBy(css = "[data-testid=\"todo-item\"]")
    private WebElement todoItem;
    @FindBy(css = "[data-testid=\"delete\"]")
    private WebElement deleteButton;
    @FindBy(css = "[data-testid=\"no-todos\"]")
    private WebElement noTodoAvailable;


    @Step
    public boolean isWelcomeMessageDisplayed() {
        return welcomeMessage.isDisplayed();
    }

    @Step
    public NewTodoPage clickOnPlusButton() {
        addNewTodo.click();
        return new NewTodoPage(driver);
    }

    @Step
    public String getTodoText() {
        return todoItem.getText();
    }

    @Step
    public TodoPage clickOnDeleteButton() {
        deleteButton.click();
        return this;
    }

    @Step
    public String getNoTodoAvailable() {
        return noTodoAvailable.getText();
    }

    @Step
    public TodoPage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.TODO_PAGE_ENDPOINT);
        return this;
    }
}
