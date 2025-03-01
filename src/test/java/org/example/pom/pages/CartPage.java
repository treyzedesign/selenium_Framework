package org.example.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.example.pom.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage {
//    private final By checkout_btn = By.xpath("//a[@class='checkout-button button alt wc-forward']");
//    private final By product_name = By.xpath("//a[normalize-space()='Blue Shoes']");
    @FindBy(xpath = "//a[normalize-space()='Blue Shoes']")
    private WebElement product_name;

    @FindBy( xpath = "//a[@class='checkout-button button alt wc-forward']")
    @CacheLookup
    private WebElement checkout_btn;

    public CartPage(WebDriver driver) {
        super(driver);
        // move the pageFactory to the BasePage constructor
        // if you are going to use it on all pages
        PageFactory.initElements(driver, this);
    }
    public String  get_product_name(){

        return product_name.getText();
    }
    public CheckoutPage click_checkout_btn (){
        checkout_btn.click();
        return new CheckoutPage(driver);
    }
}
