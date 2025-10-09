package com.example.page_factory.pages;

import org.openqa.selenium.WebDriver;
import com.example.abstractcomponents.AbstractComponents;
import com.example.page_factory.object_repository.LoginObjectRepository;

public class LoginPage extends AbstractComponents {
    private LoginObjectRepository loginObject;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.loginObject = new LoginObjectRepository(driver);
    }

    public void doLogin(String username, String password) {
        visibilityElement(loginObject.loginContainer);
        loginObject.username.sendKeys(username);
        loginObject.password.sendKeys(password);
        loginObject.loginButton.click();
    }
}
