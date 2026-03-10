package tests.bai22;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        DriverFactory.initDriver(browser);
        getDriver().get("https://www.saucedemo.com/");
    }

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}