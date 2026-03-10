package tests.bai12;

import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver driver;
    private final String baseUrl = "https://www.saucedemo.com/";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void login(String username, String password) {
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    private String getErrorMessage() {
        return driver.findElement(By.cssSelector("[data-test='error']")).getText();
    }

    @Test
    public void testLoginSuccess() {
        login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @Test
    public void testLoginWrongPassword() {
        login("standard_user", "wrong_password");
        Assert.assertTrue(getErrorMessage().contains("Username and password do not match"));
    }

    @Test
    public void testLoginEmptyUsername() {
        login("", "secret_sauce");
        Assert.assertEquals(getErrorMessage(), "Epic sadface: Username is required");
    }

    @Test
    public void testLoginEmptyPassword() {
        login("standard_user", "");
        Assert.assertEquals(getErrorMessage(), "Epic sadface: Password is required");
    }

    @Test
    public void testLoginLockedUser() {
        login("locked_out_user", "secret_sauce");
        Assert.assertTrue(getErrorMessage().contains("Sorry, this user has been locked out"));
    }
}