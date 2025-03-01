package org.example.pom.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.example.pom.factory.DriverManager;
import org.example.pom.objects.Driver;
import org.example.pom.utils.ExtentManager;
import org.example.pom.utils.JacksonUtils;
import org.example.pom.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTest {
    protected WebDriver driver;
    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        extent = ExtentManager.getReportInstance();
    }

    @Parameters("browser")
    @BeforeClass
    public void startDriver(@Optional("edge") String browser) {
        driver = new DriverManager().init_driver(browser);
        Logger.setDriver(driver);
    }

    @BeforeMethod
    public void beforeTest(Method method) {
        test = extent.createTest(method.getName());
        Logger.setTest(test);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        endTest(result);
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }
    public void endTest(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Logger.logError("Test Failed: " + result.getThrowable().getMessage());
            Logger.logScreenshot("Test Failed Here");
        } else if (result.getStatus() == ITestResult.SKIP) {
            Logger.logInfo("Test Skipped");
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            Logger.logSuccess("Test Passed");
            Logger.logScreenshot("Test Passed Here");

        }
    }
}
