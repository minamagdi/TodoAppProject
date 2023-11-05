package com.test.todo.pages;

import com.test.todo.base.BasePage;
import com.test.todo.utilies.ConfigUtils;
import io.qameta.allure.Step;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    private final By emailField = By.cssSelector("[data-testid=\"email\"]");
    private final By passwordField = By.cssSelector("[data-testid=\"password\"]");
    private final By loginBtn = By.cssSelector("[data-testid=\"submit\"]");

    @Step
    public LoginPage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl());
        return this;
    }

    @Step
    public TodoPage login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginBtn).click();
        return new TodoPage(driver);
    }


}
