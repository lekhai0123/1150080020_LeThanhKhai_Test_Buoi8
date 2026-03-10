package tests.bai22;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void testLoginSuccessParallel() {
        getDriver().findElement(By.id("user-name")).sendKeys("standard_user");
        getDriver().findElement(By.id("password")).sendKeys("secret_sauce");
        getDriver().findElement(By.id("login-button")).click();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("inventory.html"));
    }

    @Test
    public void testLoginWrongPasswordParallel() {
        getDriver().findElement(By.id("user-name")).sendKeys("standard_user");
        getDriver().findElement(By.id("password")).sendKeys("wrong_password");
        getDriver().findElement(By.id("login-button")).click();
        String error = getDriver().findElement(By.cssSelector("[data-test='error']")).getText();
        Assert.assertTrue(error.contains("Username and password do not match"));
    }
}