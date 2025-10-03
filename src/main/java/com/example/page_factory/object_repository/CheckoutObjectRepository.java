package com.example.page_factory.object_repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutObjectRepository {
    public CheckoutObjectRepository(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public By checkoutContainer = By.xpath("//div[@class = 'checkout_summary_container']");

    public By checkoutList = By.xpath("//div[@class = 'cart_item']");

    public By productName = By.xpath(".//div[@class = 'inventory_item_name']");

    public By productPrice = By.xpath(".//div[@class = 'inventory_item_price']");

    @FindBy(xpath = "//div[@class = 'summary_subtotal_label']")
    public WebElement subtotalLabel;

    @FindBy(id = "finish")
    public WebElement finishButton;
}
