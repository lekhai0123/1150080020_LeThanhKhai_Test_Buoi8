package tests.bai22;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {
    private void login() {
        getDriver().findElement(By.id("user-name")).sendKeys("standard_user");
        getDriver().findElement(By.id("password")).sendKeys("secret_sauce");
        getDriver().findElement(By.id("login-button")).click();
    }

    @Test
    public void testAddToCartParallel() {
        login();
        getDriver().findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        String badge = getDriver().findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(badge, "1");
    }

    @Test
    public void testOpenCartParallel() {
        login();
        getDriver().findElement(By.className("shopping_cart_link")).click();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("cart.html"));
    }
}