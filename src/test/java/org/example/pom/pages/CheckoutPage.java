package org.example.pom.pages;

import org.example.pom.base.BasePage;
import org.example.pom.objects.BillingAddress;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CheckoutPage extends BasePage {

    private final By firstName = By.xpath("//input[@id='billing_first_name']");
    private final By lastName = By.xpath("//input[@id='billing_last_name']");
    private final By address = By.xpath("//input[@id='billing_address_1']");
    private final By city = By.xpath("//input[@id='billing_city']");
    private final By postCode = By.xpath("//input[@id='billing_postcode']");
    private final By mail = By.xpath("//input[@id='billing_email']");
    private final By order_btn = By.xpath("/html/body/div/div[1]/div/div/main/article/div/div/div/div/form[3]/div[2]/div/div/button");
    private final By checkout_text = By.xpath("//p[@class='woocommerce-notice woocommerce-notice--success woocommerce-thankyou-order-received']");
    private final By overlay = By.xpath("/html/body/div/div[1]/div/div/main/article/div/div/div/div/form[3]/div[2]/div/div[3]");
    private final By country_dropdown = By.id("billing_country");
    private final By state_dropdown = By.id("billing_state");
    private final By pay_method_btn= By.id("payment_method_cod");
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private CheckoutPage select_country(String country){
        Select select = new Select(driver.findElement(country_dropdown));
        select.selectByVisibleText("Algeria");
        return this;
    }
    private CheckoutPage select_state(String state){
        Select select = new Select(driver.findElement(state_dropdown));
        select.selectByVisibleText(state);
        return this;
    }

    public CheckoutPage fill_form(BillingAddress billingAddress){
        driver.findElement(firstName).sendKeys(billingAddress.getFirstname());
        driver.findElement(lastName).sendKeys(billingAddress.getLastname());
        this.select_country(billingAddress.getCountry());
        driver.findElement(address).sendKeys(billingAddress.getAddress());
        driver.findElement(city).sendKeys(billingAddress.getCity());
        this.select_state(billingAddress.getState());
        driver.findElement(postCode).sendKeys(billingAddress.getZipcode());
        driver.findElement(mail).sendKeys(billingAddress.getEmail());
        return this;
    }
    public CheckoutPage click_pay_method_btn(){
        wait_for_overlays_to_disappear(overlay);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(pay_method_btn));
        if(!element.isSelected()){
            element.click();
        }
        return this;
    }
    public void place_order(){

        wait_for_overlays_to_disappear(overlay);
        driver.findElement(order_btn).click();
//        try{
//            WebElement element = new WebDriverWait(driver, 20).until(
//                    ExpectedConditions.elementToBeClickable(order_btn)
//            );
//            element.click();
//
//        }catch (org.openqa.selenium.StaleElementReferenceException ex){
//            WebElement element = new WebDriverWait(driver, 20).until(
//                    ExpectedConditions.elementToBeClickable(order_btn)
//            );
//            element.click();
//        }


    }

//    public   setBillingInfo(BillingAddress billingAddress){
//        fill_form(billingAddress);
//    }

    public  String get_checkout_text(){
        return driver.findElement(checkout_text).getText();
    }

}
