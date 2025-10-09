package com.example.page_factory.object_repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InformationObjectRepository {

    public InformationObjectRepository(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public By informationContainer = By.xpath("//div[@class = 'checkout_info_container']");

    @FindBy(id = "first-name")
    public WebElement firstName;

    @FindBy(id = "last-name")
    public WebElement lastName;

    @FindBy(id = "postal-code")
    public WebElement postalCode;

    @FindBy(id = "continue")
    public WebElement continueButton;

    @FindBy(xpath = "//div[@class = 'error-message-container error']")
    public WebElement errorMessage;
}
