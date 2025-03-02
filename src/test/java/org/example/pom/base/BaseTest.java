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
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static ExtentReports extent;
    protected static ExtentTest test;
    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }
    @BeforeSuite
    public void setupReport() {
        extent = ExtentManager.getReportInstance();
    }

    @Parameters("browser")
    @BeforeClass
    public void startDriver(@Optional("chrome") String browser) {
        WebDriver webDriver = new DriverManager().init_driver(browser);
        setDriver(webDriver); // Store WebDriver in ThreadLocal
        Logger.setDriver(getDriver());  // Ensure logging uses ThreadLocal WebDriver
    }

    @BeforeMethod
    public void beforeTest(Method method) {
        test = extent.createTest(method.getName());
        Logger.setTest(test);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        endTest(result);
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove(); // Remove ThreadLocal instance after quitting driver
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
