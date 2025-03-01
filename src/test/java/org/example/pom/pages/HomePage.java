package org.example.pom.pages;

import org.example.pom.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private final By storeMenuLink = By.cssSelector("#menu-item-1227");
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage load(){
        load("/");
        return this;
    }
    public StorePage clickStoreLink(){
        click(storeMenuLink);
        return new StorePage(driver);
    }
}
