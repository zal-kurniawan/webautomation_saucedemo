package com.example.page_factory.pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.example.page_factory.object_repository.ProductsObjectRepository;

public class ProductPage {
    private ProductsObjectRepository productObject;
    private WebDriver driver;
    private WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        productObject = new ProductsObjectRepository(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofMillis(40000));
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(productObject.productContainer));
        for (String productName : productNames) {
            getProductByName(productName).findElement(productObject.buttonAddToCart).click();
        }
    }

    public void goToCartPage() {
        productObject.cartButton.click();
    }
}
