package com.test.todo.pages;

import com.test.todo.base.BasePage;
import com.test.todo.config.EndPoint;
import com.test.todo.utilies.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewTodoPage extends BasePage {
    @FindBy(css = "[data-testid=\"new-todo\"]")
    WebElement newToDo;
    @FindBy(css = "[data-testid=\"submit-newTask\"]")
    WebElement submitNewToDo;

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
        newToDo.sendKeys(todoName);
        submitNewToDo.click();
        return new TodoPage(driver);
    }

}

