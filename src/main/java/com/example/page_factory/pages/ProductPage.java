package com.example.page_factory.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.abstractcomponents.AbstractComponents;
import com.example.page_factory.object_repository.ProductsObjectRepository;

public class ProductPage extends AbstractComponents {
    private ProductsObjectRepository productObject;
    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        super(driver);
        productObject = new ProductsObjectRepository(driver);
        this.driver = driver;
    }

    public WebElement getProductByName(String productName) {
        List<WebElement> listProduct = driver.findElements(productObject.productList);
        WebElement product = listProduct.stream()
                .filter(prod -> prod.findElement(productObject.productName)
                        .getText()
                        .equals(productName))
                .findFirst().orElse(null);
        return product;
    }

    public void addToCartProduct(String[] productNames) {
        visibilityElement(productObject.productContainer);
        for (String productName : productNames) {
            getProductByName(productName).findElement(productObject.buttonAddToCart).click();
        }
    }

    public void goToCartPage() {
        productObject.cartButton.click();
    }
}
