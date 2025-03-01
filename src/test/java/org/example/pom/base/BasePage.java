package org.example.pom.base;

import org.example.pom.utils.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage (WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public void load(String endpoint){
        driver.get("https://askomdch.com" + endpoint);
    }

    public void wait_for_overlays_to_disappear(By element){
        List<WebElement> overlays= driver.findElements(element);
        if(overlays.size() > 0){
            wait.until(
                    ExpectedConditions.invisibilityOfAllElements(overlays)
            );
        }
    }
    //Click Element
    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        Logger.logInfo("Clicked element: " + locator);
    }

    //Fill Input Field
    public void fillElement(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
        Logger.logInfo("Entered text in: " + locator + " -> " + text);
    }

    //Wait for Element to be Visible
    public void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Logger.logInfo("Waited for visibility of element: " + locator);
    }
    //Wait for Element to be Visible
    public void waitForClickability(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        Logger.logInfo("Waited for clickability of element: " + locator);
    }

}
