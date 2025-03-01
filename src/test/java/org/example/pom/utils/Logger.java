package org.example.pom.utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.Base64;

public class Logger {
    private static ExtentTest test;
    private static WebDriver driver;
    public static void setTest(ExtentTest extentTest) {
        test = extentTest;
    }
    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    public static void logInfo(String message) {
        if (test != null) {
            test.log(Status.INFO, message);
        }
    }

    public static void logError(String message) {
        if (test != null) {
            test.log(Status.FAIL, message);
        }
    }

    public static void logSuccess(String message) {
        if (test != null) {
            test.log(Status.PASS, message);
        }
    }
    public static void logScreenshot(String info) {
        try {
            if (driver != null) {
                TakesScreenshot ts = (TakesScreenshot) driver;
                String base64Screenshot = Base64.getEncoder().encodeToString(ts.getScreenshotAs(OutputType.BYTES));

                if (test != null) {
                    test.info(info, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
                }
                System.out.println("[SCREENSHOT] " + info);
            }
        } catch (Exception e) {
            logError("Failed to take screenshot: " + e.getMessage());
        }
    }
}
