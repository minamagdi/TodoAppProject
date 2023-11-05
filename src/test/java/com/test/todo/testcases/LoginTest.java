package com.test.todo.testcases;

import com.test.todo.base.BaseTest;
import com.test.todo.pages.LoginPage;
import com.test.todo.utilies.ConfigUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("auth feature")
public class LoginTest extends BaseTest {

    @Story("Login with valid credential")
    @Description("it will login by filling email and password and navigate to Todo page")
    
    @Test(description = "Test login Functionality using email and password")
    public void shouldBeAbleToLoginWithEmailAndPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        boolean isWelcomeMessageDisplayed =
                loginPage
                        .load()
                        .login(ConfigUtils.getInstance().getEmail(), ConfigUtils.getInstance().getPassword())
                        .isWelcomeMessageDisplayed();
        Assert.assertTrue(isWelcomeMessageDisplayed);
    }
}
