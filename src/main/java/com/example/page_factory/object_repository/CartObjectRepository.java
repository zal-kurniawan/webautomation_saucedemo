package com.example.page_factory.object_repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartObjectRepository {
    public CartObjectRepository(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public By cartContainer = By.xpath("//div[@class = 'cart_list']");

    public By cartList = By.xpath("//div[@class = 'cart_item']");

    public By productName = By.xpath(".//div[@class = 'inventory_item_name']");

    @FindBy(id = "checkout")
    public WebElement checkoutButton;
}
