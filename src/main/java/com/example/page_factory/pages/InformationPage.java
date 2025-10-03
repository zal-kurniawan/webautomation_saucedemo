package com.example.page_factory.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.example.page_factory.object_repository.InformationObjectRepository;

public class InformationPage {
    InformationObjectRepository informationObject;
    WebDriverWait wait;

    public InformationPage(WebDriver driver) {
        informationObject = new InformationObjectRepository(driver);
        wait = new WebDriverWait(driver, Duration.ofMillis(40000));
    }

    public void fillInformationCheckout(String firstName, String lastName, String postalCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(informationObject.informationContainer));
        informationObject.firstName.sendKeys(firstName);
        informationObject.lastName.sendKeys(lastName);
        informationObject.postalCode.sendKeys(postalCode);
        informationObject.continueButton.click();
    }
}
