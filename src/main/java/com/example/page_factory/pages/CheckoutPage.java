package com.example.page_factory.pages;

import org.openqa.selenium.WebDriver;
import com.example.page_factory.object_repository.CheckoutObjectRepository;

public class CheckoutPage {
    private CheckoutObjectRepository checkoutObject;

    public CheckoutPage(WebDriver driver) {
        checkoutObject = new CheckoutObjectRepository(driver);
    }

    public void finishCheckout() {
        checkoutObject.finishButton.click();
    }
}
