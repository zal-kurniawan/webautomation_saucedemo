package com.example.page_factory.object_repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CompleteCheckoutObjectRepository {
    public CompleteCheckoutObjectRepository(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public By checkoutCompleteContainer = By.xpath("//div[@class = 'checkout_complete_container']");

    @FindBy(xpath = "//h2[@class = 'complete-header']")
    public WebElement completeHeader;

    @FindBy(xpath = "//div[@class = 'complete-text']")
    public WebElement completeText;

    @FindBy(id = "back-to-products")
    public WebElement backHomeButton;
}
