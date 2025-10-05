package com.example.page_factory.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import com.example.page_factory.object_repository.CheckoutObjectRepository;

public class CheckoutPage {
    private CheckoutObjectRepository checkoutObject;
    private WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        checkoutObject = new CheckoutObjectRepository(driver);
        this.driver = driver;
    }

    public List<String> getItemNames() {
        List<String> itemNamesCheckout = driver.findElements(checkoutObject.checkoutList).stream()
                .map(name -> name.findElement(checkoutObject.productName)
                        .getText())
                .toList();
        return itemNamesCheckout;
    }

    public List<String> getItemPrices() {
        List<String> itemPrices = driver.findElements(checkoutObject.checkoutList).stream()
                .map(name -> name.findElement(checkoutObject.productPrice)
                        .getText())
                .toList();
        return itemPrices;
    }

    public void finishCheckout() {
        checkoutObject.finishButton.click();
    }
}
