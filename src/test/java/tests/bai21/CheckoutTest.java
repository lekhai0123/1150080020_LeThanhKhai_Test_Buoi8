package tests.bai21;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest {
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

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
    }

    @Test(groups = {"smoke", "regression"})
    public void testCheckoutInfoPageDisplayed() {
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"));
    }

    @Test(groups = {"regression"})
    public void testCheckoutWithoutInfo() {
        driver.findElement(By.id("continue")).click();
        String error = driver.findElement(By.cssSelector("[data-test='error']")).getText();
        Assert.assertTrue(error.contains("First Name is required"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}