package com.example.page_factory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.page_factory.object_repository.CartObjectRepository;

public class CartPage {
    private CartObjectRepository cartObject;

    public CartPage(WebDriver driver) {
        cartObject = new CartObjectRepository(driver);
    }

    public void goToCheckoutOverview() {
        cartObject.checkoutButton.click();
    }
}
