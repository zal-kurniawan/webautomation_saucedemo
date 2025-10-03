package com.example.page_factory.pages;

import org.openqa.selenium.WebDriver;
import com.example.page_factory.object_repository.CompleteCheckoutObjectRepository;

public class CompleteCheckoutPage {
    private CompleteCheckoutObjectRepository completeObject;

    public CompleteCheckoutPage(WebDriver driver) {
        completeObject = new CompleteCheckoutObjectRepository(driver);
    }

    public void backHome() {
        completeObject.backHomeButton.click();
    }
}
