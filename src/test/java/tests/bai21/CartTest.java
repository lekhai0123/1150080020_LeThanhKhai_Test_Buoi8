package tests.bai21;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @Test(groups = {"smoke", "regression"})
    public void testAddToCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        String badge = driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(badge, "1");
    }

    @Test(groups = {"regression"})
    public void testOpenCartPage() {
        driver.findElement(By.className("shopping_cart_link")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}