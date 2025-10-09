package com.example.page_factory.pages;

import org.openqa.selenium.WebDriver;
import com.example.abstractcomponents.AbstractComponents;
import com.example.page_factory.object_repository.InformationObjectRepository;

public class InformationPage extends AbstractComponents {
    InformationObjectRepository informationObject;

    public InformationPage(WebDriver driver) {
        super(driver);
        informationObject = new InformationObjectRepository(driver);
    }

    public void fillInformationCheckout(String firstName, String lastName, String postalCode) {
        visibilityElement(informationObject.informationContainer);
        informationObject.firstName.sendKeys(firstName);
        informationObject.lastName.sendKeys(lastName);
        informationObject.postalCode.sendKeys(postalCode);
    }

    public void goToOverviewPage() {
        informationObject.continueButton.click();
    }

    public void verifyErrorMessageInformation() {
        informationObject.errorMessage.isDisplayed();
    }
}
