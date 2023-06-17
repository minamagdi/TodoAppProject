package com.test.todo.base;

import com.test.todo.factory.DriverFactory;
import com.test.todo.utilies.CookieUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.http.Cookie;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BaseTest {
    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    public WebDriver getDriver() {
        return this.driver.get();
    }

    @BeforeMethod
    public void setup() {
        WebDriver driver = new DriverFactory().initializeDriver();
        setDriver(driver);
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        /* hard coded
        File file = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file,new File ("screenShots/image1.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        String testCaseName = result.getMethod().getMethodName();
        File destinationFile = new File("target" + File.separator + "screenShots"
                + File.separator + testCaseName + ".png");
        takeScreenShot(destinationFile);
        getDriver().quit();
    }

    @Step
    public void injectCookiesToBrowser(List<Cookie> restAssuredCookies) {
        List<org.openqa.selenium.Cookie> seleniumCookies = CookieUtils.convertRestAssuredToSeleniumCookies(restAssuredCookies);
        for (org.openqa.selenium.Cookie cookie : seleniumCookies) {
            getDriver().manage().addCookie(cookie);
        }
    }

    public void takeScreenShot(File destinationFile) {
        File file = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, destinationFile);
            InputStream is = new FileInputStream(destinationFile);
            Allure.addAttachment("screenShots", is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
