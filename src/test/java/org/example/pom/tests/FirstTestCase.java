package org.example.pom.tests;

import org.example.pom.base.BaseTest;
import org.example.pom.objects.BillingAddress;
import org.example.pom.objects.Products;
import org.example.pom.pages.CartPage;
import org.example.pom.pages.CheckoutPage;
import org.example.pom.pages.HomePage;
import org.example.pom.pages.StorePage;
import org.example.pom.utils.JacksonUtils;
import org.example.pom.utils.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class FirstTestCase extends BaseTest {

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {
        Logger.logInfo("starting test - guest Checkout Using Direct Bank Transfer");
        BillingAddress billingAddress = JacksonUtils.deserializeJson("BillingAddress.json", BillingAddress.class);
        Products products = new Products(1215);
        // Page objects
        HomePage homePage = new HomePage(getDriver()).load();
        StorePage storePage = homePage.clickStoreLink();
        storePage.search("blue");
        Assert.assertEquals(storePage.getTitle(), "Search results: “blue”");
        storePage.clickAddToCartBtn();
        CartPage cartPage = storePage.click_view_cart_link();
        Assert.assertEquals( cartPage.get_product_name() ,"Blue Shoes");
        CheckoutPage checkoutPage = cartPage.click_checkout_btn();
        checkoutPage.fill_form(billingAddress).
                click_pay_method_btn().
                place_order();
        Assert.assertEquals(checkoutPage.get_checkout_text(), "Thank you. Your order has been received.");
    }

}

