import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
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

        @BeforeClass
        public void setUp() {
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
        }

        @Test
        public void endToEndCheckout() throws InterruptedException {
                // Login
                loginPage.doLogin("standard_user", "secret_sauce");
                Thread.sleep(2000);

                // Add to card product
                String[] productsName = { "Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie" };
                productPage.addToCartProduct(productsName);
                productPage.goToCartPage();
                Thread.sleep(2000);

                // Cart page
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'cart_list']")));
                List<String> itemNames = driver.findElements(By.xpath("//div[@class = 'cart_item']")).stream()
                                .map(name -> name.findElement(By.xpath(".//div[@class = 'inventory_item_name']"))
                                                .getText())
                                .toList();
                Assert.assertEquals(itemNames.toArray(), productsName,
                                "Daftar produk di keranjang tidak sesuai.");
                driver.findElement(By.id("checkout")).click();
                Thread.sleep(2000);

                // Fill information checkout
                informationPage.fillInformationCheckout("Budi", "Sudarsono", "654921");
                Thread.sleep(2000);

                // Overview product

                wait.until(ExpectedConditions
                                .visibilityOfElementLocated(By.xpath("//div[@class = 'checkout_summary_container']")));
                List<String> itemNamesOverview = driver.findElements(By.xpath("//div[@class = 'cart_item']")).stream()
                                .map(name -> name.findElement(By.xpath(".//div[@class = 'inventory_item_name']"))
                                                .getText())
                                .toList();
                Assert.assertEquals(itemNamesOverview.toArray(), productsName,
                                "Daftar produk di keranjang tidak sesuai.");
                List<String> itemPrices = driver.findElements(By.xpath("//div[@class = 'cart_item']")).stream()
                                .map(name -> name.findElement(By.xpath(".//div[@class = 'inventory_item_price']"))
                                                .getText())
                                .toList();
                float totalPrice = 0;
                for (String price : itemPrices) {
                        price = price.replaceAll("\\$", "");
                        totalPrice = totalPrice + Float.parseFloat(price);
                }
                Assert.assertEquals(driver.findElement(By.xpath("//div[@class = 'summary_subtotal_label']")).getText(),
                                "Item total: $" + totalPrice, "Subtotal produk tidak sesuai.");
                driver.findElement(By.id("finish")).click();
                Thread.sleep(2000);

                // Verify checkout complete
                wait.until(ExpectedConditions
                                .visibilityOfElementLocated(By.xpath("//div[@class = 'checkout_complete_container']")));
                driver.findElement(By.xpath("//h2[@class = 'complete-header']")).isDisplayed();
                driver.findElement(By.xpath("//div[@class = 'complete-text']")).isDisplayed();
        }

        @AfterClass
        public void tearDown() {
                if (driver != null) {
                        driver.quit();
                }
        }

}
