import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.example.page_factory.object_repository.CartObjectRepository;
import com.example.page_factory.object_repository.CheckoutObjectRepository;
import com.example.page_factory.object_repository.CompleteCheckoutObjectRepository;
import com.example.page_factory.pages.CartPage;
import com.example.page_factory.pages.CheckoutPage;
import com.example.page_factory.pages.CompleteCheckoutPage;
import com.example.page_factory.pages.InformationPage;
import com.example.page_factory.pages.LoginPage;
import com.example.page_factory.pages.ProductPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ScenarioE2ETest {
        WebDriver driver;
        WebDriverWait wait;
        private LoginPage loginPage;
        private ProductPage productPage;
        private InformationPage informationPage;
        private CartObjectRepository cartObject;
        private CartPage cartPage;
        private CheckoutObjectRepository checkoutObject;
        private CheckoutPage checkoutPage;
        private CompleteCheckoutObjectRepository completeObject;
        private CompleteCheckoutPage completePage;

        @BeforeClass
        public void setUp() {
                // Setup browser
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--guest");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                // Navigate to a website
                driver.get("https://www.saucedemo.com/");
                wait = new WebDriverWait(driver, Duration.ofMillis(40000));
                /*
                 * wait akan berhenti kalau sudah menemukan element yang dicari
                 * thread.sleep() akan nunggu sampe waktu habis
                 */
                loginPage = new LoginPage(driver);
                productPage = new ProductPage(driver);
                informationPage = new InformationPage(driver);
                cartObject = new CartObjectRepository(driver);
                cartPage = new CartPage(driver);
                checkoutObject = new CheckoutObjectRepository(driver);
                checkoutPage = new CheckoutPage(driver);
                completeObject = new CompleteCheckoutObjectRepository(driver);
                completePage = new CompleteCheckoutPage(driver);
        }

        @Test
        public void endToEndCheckout() throws InterruptedException {
                // Login
                loginPage.doLogin("standard_user", "secret_sauce");
                Thread.sleep(2000);

                // Add to card product
                String[] productNames = { "Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt",
                                "Sauce Labs Onesie" };
                productPage.addToCartProduct(productNames);
                productPage.goToCartPage();
                Thread.sleep(2000);

                // Cart page
                wait.until(ExpectedConditions.visibilityOfElementLocated(cartObject.cartContainer));
                Assert.assertEquals(cartPage.getItemNames().toArray(), productNames,
                                "Daftar produk di keranjang tidak sesuai.");
                cartPage.goToInformationPage();
                Thread.sleep(2000);

                // Fill information checkout
                informationPage.fillInformationCheckout("Budi", "Sudarsono", "654921");
                informationPage.goToOverviewPage();
                Thread.sleep(2000);

                // Overview product
                wait.until(ExpectedConditions
                                .visibilityOfElementLocated(checkoutObject.checkoutContainer));
                Assert.assertEquals(checkoutPage.getItemNames().toArray(), productNames,
                                "Daftar produk di keranjang tidak sesuai.");
                float totalPrice = 0;
                for (String price : checkoutPage.getItemPrices()) {
                        price = price.replaceAll("\\$", "");
                        totalPrice = totalPrice + Float.parseFloat(price);
                }
                Assert.assertEquals(checkoutObject.subtotalLabel.getText(),
                                "Item total: $" + totalPrice, "Subtotal produk tidak sesuai.");
                checkoutPage.finishCheckout();
                Thread.sleep(2000);

                // Verify checkout complete
                wait.until(ExpectedConditions
                                .visibilityOfElementLocated(completeObject.checkoutCompleteContainer));
                completeObject.completeHeader.isDisplayed();
                completeObject.completeText.isDisplayed();
                completePage.backHome();
                Thread.sleep(2000);
        }

        @AfterClass
        public void tearDown() {
                if (driver != null) {
                        driver.quit();
                }
        }

}
