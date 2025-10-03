package com.example.page_factory.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.example.page_factory.object_repository.LoginObjectRepository;

public class LoginPage {
    private LoginObjectRepository loginObject;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.loginObject = new LoginObjectRepository(driver);
        wait = new WebDriverWait(driver, Duration.ofMillis(40000));
    }

    public void doLogin(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginObject.loginContainer));
        loginObject.username.sendKeys(username);
        loginObject.password.sendKeys(password);
        loginObject.loginButton.click();
    }
}
