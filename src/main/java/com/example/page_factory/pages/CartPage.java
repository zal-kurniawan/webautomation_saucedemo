package com.example.page_factory.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import com.example.page_factory.object_repository.CartObjectRepository;

public class CartPage {
    private CartObjectRepository cartObject;
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        cartObject = new CartObjectRepository(driver);
        this.driver = driver;
    }

    public List<String> getItemNames() {
        List<String> itemNamesCart = driver.findElements(cartObject.cartList).stream()
                .map(name -> name.findElement(cartObject.productName)
                        .getText())
                .toList();
        return itemNamesCart;
    }

    public void goToCheckoutOverview() {
        cartObject.checkoutButton.click();
    }
}
