package stepdefenitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.example.page_factory.object_repository.CheckoutObjectRepository;
import com.example.page_factory.pages.CartPage;
import com.example.page_factory.pages.CheckoutPage;
import com.example.page_factory.pages.CompleteCheckoutPage;
import com.example.page_factory.pages.InformationPage;
import com.example.page_factory.pages.LoginPage;
import com.example.page_factory.pages.ProductPage;
import hook.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepImplementation {
    WebDriver driver;
    String[] products;

    public StepImplementation(Hooks hooks) {
        this.driver = Hooks.getDriver();
    }

    @When("User login with valid credentials")
    public void userLoginWithValidCredentials() {
        // Write code here that turns the phrase above into concrete actions
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("standard_user", "secret_sauce");
    }

    @Then("User is navigated to the products page")
    public void userNavigatedToTheProductsPage() throws InterruptedException {
        Thread.sleep(2000);
    }

    @When("User adds a product to the cart")
    public void userAddsProductToTheCart() {
        ProductPage productPage = new ProductPage(driver);
        products = new String[] { "Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt" };
        productPage.addToCartProduct(products);
    }

    @And("User click on the cart icon")
    public void userClickOnTheCartIcon() throws InterruptedException {
        ProductPage productPage = new ProductPage(driver);
        productPage.goToCartPage();
        Thread.sleep(2000);
    }

    @Then("User verify the product is added to the cart")
    public void userVerifyTheProductIsAddedToTheCart() throws InterruptedException {
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getItemNames().toArray(), products,
                "Daftar produk di keranjang tidak sesuai.");
        Thread.sleep(2000);
    }

    @When("User click on the checkout buton")
    public void userClickOnTheCheckoutButton() {
        CartPage cartPage = new CartPage(driver);
        cartPage.goToInformationPage();

    }

    @Then("User navigated to the information page")
    public void userNavigatedToTheInformationPage() throws InterruptedException {
        Thread.sleep(2000);
    }

    @When("User fill information")
    public void userFillInformation() {
        InformationPage informationPage = new InformationPage(driver);
        informationPage.fillInformationCheckout("Budi", "Sudarsono", "651239");
    }

    @And("User click to the continue button")
    public void userClickToTheContinueButton() throws InterruptedException {
        InformationPage informationPage = new InformationPage(driver);
        informationPage.goToOverviewPage();
        Thread.sleep(2000);
    }

    @Then("User verify the product on the checkout page")
    public void userVerifyTheProductOnTheCheckoutPage() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        CheckoutObjectRepository checkoutObject = new CheckoutObjectRepository(driver);
        Assert.assertEquals(checkoutPage.getItemNames().toArray(), products,
                "Daftar produk di keranjang tidak sesuai.");
        float totalPrice = 0;
        for (String price : checkoutPage.getItemPrices()) {
            price = price.replaceAll("\\$", "");
            totalPrice = totalPrice + Float.parseFloat(price);
        }
        Assert.assertEquals(checkoutObject.subtotalLabel.getText(),
                "Item total: $" + totalPrice, "Subtotal produk tidak sesuai.");
    }

    @When("User click on the finish button")
    public void userClickOnTheFinishButton() throws InterruptedException {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.finishCheckout();
        Thread.sleep(2000);
    }

    @Then("User verify the order is placed successfully")
    public void userVerifyTheOrderIsPlacedSuccessfully() {
        CompleteCheckoutPage completeCheckoutPage = new CompleteCheckoutPage(driver);
        completeCheckoutPage.verifyCompleteCheckout();

    }

    @When("User login with invalid credentials")
    public void userLoginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("admin", "admin");
    }

    @Then("User will see error message login")
    public void userWillSeeErrorMessageLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyErrorLogin();
    }

    @When("User fill information with {string}, {string}, and {string}")
    public void userFillInformationWith(String firstname, String lastname, String postalcode) {
        InformationPage informationPage = new InformationPage(driver);
        informationPage.fillInformationCheckout(firstname, lastname, postalcode);
    }

    @Then("User will see error messaage information")
    public void userWillSeeErrorMessageInformation() {
        InformationPage informationPage = new InformationPage(driver);
        informationPage.verifyErrorMessageInformation();
    }
}
