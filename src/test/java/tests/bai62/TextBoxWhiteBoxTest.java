package tests.bai62;

import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TextBoxWhiteBoxTest {
    private WebDriver driver;
    private TextBoxPage textBoxPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demoqa.com/text-box");
        textBoxPage = new TextBoxPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(description = "Valid path: nhap du lieu hop le va output hien thi")
    public void testValidSubmit() {
        textBoxPage.fillAndSubmit(
                "Nguyen Van A",
                "vana@gmail.com",
                "123 Nguyen Trai",
                "456 Le Loi"
        );

        Assert.assertTrue(textBoxPage.isOutputDisplayed(), "Output phai hien thi khi du lieu hop le");
        Assert.assertTrue(textBoxPage.getOutputText().contains("Nguyen Van A"), "Output phai chua ten da nhap");
        Assert.assertTrue(textBoxPage.getOutputText().contains("vana@gmail.com"), "Output phai chua email da nhap");
    }

    @Test(description = "Boundary: name rong, email hop le")
    public void testEmptyNameValidEmail() {
        textBoxPage.fillAndSubmit(
                "",
                "test@gmail.com",
                "Dia chi hien tai",
                "Dia chi thuong tru"
        );

        Assert.assertTrue(textBoxPage.isOutputDisplayed(), "Output van co the hien thi khi ten rong");
    }

    @Test(description = "Boundary: name chi co khoang trang")
    public void testNameOnlySpaces() {
        textBoxPage.fillAndSubmit(
                "   ",
                "space@gmail.com",
                "Dia chi A",
                "Dia chi B"
        );

        Assert.assertTrue(textBoxPage.isOutputDisplayed(), "Output van duoc hien thi voi ten la khoang trang");
    }

    @Test(description = "Boundary: email sai dinh dang")
    public void testInvalidEmailFormat() {
        textBoxPage.fillAndSubmit(
                "Nguyen Van B",
                "abc123",
                "Dia chi hien tai",
                "Dia chi thuong tru"
        );

        Assert.assertFalse(textBoxPage.isOutputDisplayed(), "Output khong duoc hien thi khi email sai dinh dang");
        Assert.assertTrue(textBoxPage.isEmailFieldInvalid(), "Email field phai bi danh dau loi");
    }

    @Test(description = "Boundary: ky tu dac biet trong name")
    public void testSpecialCharacterName() {
        textBoxPage.fillAndSubmit(
                "@@@###",
                "special@gmail.com",
                "Dia chi hien tai",
                "Dia chi thuong tru"
        );

        Assert.assertTrue(textBoxPage.isOutputDisplayed(), "Output phai hien thi voi ten co ky tu dac biet");
        Assert.assertTrue(textBoxPage.getOutputText().contains("@@@###"), "Output phai chua ten ky tu dac biet");
    }

    @Test(description = "Boundary: address rong")
    public void testEmptyAddress() {
        textBoxPage.fillAndSubmit(
                "Le Thi B",
                "lethib@gmail.com",
                "",
                ""
        );

        Assert.assertTrue(textBoxPage.isOutputDisplayed(), "Output phai hien thi khi dia chi rong va email hop le");
    }

    @Test(description = "Boundary: current address dai")
    public void testLongAddress() {
        String longAddress = "So 1 Duong ABC Phuong XYZ Quan 1 Thanh pho Ho Chi Minh Viet Nam ".repeat(5);

        textBoxPage.fillAndSubmit(
                "Tran Van C",
                "tranvanc@gmail.com",
                longAddress,
                longAddress
        );

        Assert.assertTrue(textBoxPage.isOutputDisplayed(), "Output phai hien thi voi dia chi dai");
        Assert.assertTrue(textBoxPage.getOutputText().contains("Tran Van C"), "Output phai chua ten da nhap");
    }
}