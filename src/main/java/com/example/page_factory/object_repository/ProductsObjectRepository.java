package com.example.page_factory.object_repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsObjectRepository {

    public ProductsObjectRepository(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public By productContainer = By.xpath("//div[@class = 'inventory_container']");

    public By productList = By.xpath("//div[@class = 'inventory_item']");

    public By productName = By.xpath(".//div[@class = 'inventory_item_name ']");

    public By buttonAddToCart = By.xpath(".//button");

    @FindBy(xpath = "//a[@class = 'shopping_cart_link']")
    public WebElement buttonCart;
}
