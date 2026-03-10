package tests.bai71;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import org.testng.annotations.Listeners;

@Listeners({AllureTestListener.class})
@Feature("Xu ly don hang")
public class OrderProcessorTest {
    private final OrderProcessor orderProcessor = new OrderProcessor();

    private List<Item> items(double... prices) {
        return Arrays.stream(prices).mapToObj(Item::new).toList();
    }

    @Test(description = "Basis Path 1: Gio hang null")
    @Story("Basis Path")
    @Description("Kiem thu truong hop gio hang null phai nem ngoai le")
    @Severity(SeverityLevel.CRITICAL)
    public void testPath1_EmptyCart_Null() {
        Assert.expectThrows(IllegalArgumentException.class,
                () -> orderProcessor.calculateTotal(null, null, "NONE", "COD"));
    }

    @Test(description = "Basis Path 2: Khong coupon, khong member, COD, total < 500k")
    @Story("Basis Path")
    @Description("Khong giam gia, COD, co phi ship 20k")
    @Severity(SeverityLevel.NORMAL)
    public void testPath2_NoCouponNoMember_COD() {
        double total = orderProcessor.calculateTotal(items(100000, 200000), null, "NONE", "COD");
        Assert.assertEquals(total, 320000.0, 0.01);
    }

    @Test(description = "Basis Path 3: Khong coupon, khong member, online, total < 500k")
    @Story("Basis Path")
    @Description("Khong giam gia, online, phi ship 30k")
    @Severity(SeverityLevel.NORMAL)
    public void testPath3_NoCouponNoMember_Online() {
        double total = orderProcessor.calculateTotal(items(100000, 200000), null, "NONE", "BANK");
        Assert.assertEquals(total, 330000.0, 0.01);
    }

    @Test(description = "Basis Path 4: Coupon SALE10, khong member, COD")
    @Story("Basis Path")
    @Description("Coupon SALE10 duoc ap dung, COD, total < 500k")
    @Severity(SeverityLevel.CRITICAL)
    public void testPath4_Sale10_NoMember_COD() {
        double total = orderProcessor.calculateTotal(items(100000, 200000), "SALE10", "NONE", "COD");
        Assert.assertEquals(total, 290000.0, 0.01);
    }

    @Test(description = "Basis Path 5: Coupon SALE20, GOLD, online")
    @Story("Basis Path")
    @Description("Coupon SALE20 va member GOLD")
    @Severity(SeverityLevel.CRITICAL)
    public void testPath5_Sale20_Gold_Online() {
        double total = orderProcessor.calculateTotal(items(200000, 200000), "SALE20", "GOLD", "BANK");
        Assert.assertEquals(total, 334000.0, 0.01);
    }

    @Test(description = "Basis Path 6: Coupon SALE20, PLATINUM, total >= 500k")
    @Story("Basis Path")
    @Description("Coupon SALE20 va member PLATINUM, khong tinh phi ship")
    @Severity(SeverityLevel.CRITICAL)
    public void testPath6_Sale20_Platinum_NoShip() {
        double total = orderProcessor.calculateTotal(items(400000, 400000), "SALE20", "PLATINUM", "BANK");
        Assert.assertEquals(total, 576000.0, 0.01);
    }

    @Test(description = "Basis Path 7: Coupon khong hop le")
    @Story("Basis Path")
    @Description("Coupon khong thuoc SALE10 hoac SALE20 phai nem ngoai le")
    @Severity(SeverityLevel.CRITICAL)
    public void testPath7_InvalidCoupon() {
        Assert.expectThrows(IllegalArgumentException.class,
                () -> orderProcessor.calculateTotal(items(100000, 100000), "ABC", "NONE", "COD"));
    }

    @Test(description = "Basis Path 8: Khong coupon, GOLD, COD")
    @Story("Basis Path")
    @Description("Member GOLD duoc giam 5 phan tram")
    @Severity(SeverityLevel.NORMAL)
    public void testPath8_NoCoupon_Gold_COD() {
        double total = orderProcessor.calculateTotal(items(100000, 200000), null, "GOLD", "COD");
        Assert.assertEquals(total, 305000.0, 0.01);
    }

    @Test(description = "Basis Path 9: Khong coupon, PLATINUM, online")
    @Story("Basis Path")
    @Description("Member PLATINUM duoc giam 10 phan tram, phi ship online")
    @Severity(SeverityLevel.NORMAL)
    public void testPath9_NoCoupon_Platinum_Online() {
        double total = orderProcessor.calculateTotal(items(100000, 200000), null, "PLATINUM", "BANK");
        Assert.assertEquals(total, 300000.0, 0.01);
    }

    @Test(description = "MC/DC cho D2: couponCode = null")
    @Story("MC/DC")
    @Description("Kiem thu dieu kien A trong D2")
    @Severity(SeverityLevel.NORMAL)
    public void testMcdc_D2_NullCoupon() {
        double total = orderProcessor.calculateTotal(items(100000, 200000), null, "NONE", "COD");
        Assert.assertEquals(total, 320000.0, 0.01);
    }

    @Test(description = "MC/DC cho D2: couponCode rong")
    @Story("MC/DC")
    @Description("Kiem thu dieu kien B trong D2")
    @Severity(SeverityLevel.NORMAL)
    public void testMcdc_D2_EmptyCoupon() {
        double total = orderProcessor.calculateTotal(items(100000, 200000), "", "NONE", "COD");
        Assert.assertEquals(total, 320000.0, 0.01);
    }

    @Test(description = "MC/DC cho D3: coupon SALE10")
    @Story("MC/DC")
    @Description("Kiem thu nhanh true cua D3")
    @Severity(SeverityLevel.NORMAL)
    public void testMcdc_D3_Sale10() {
        double total = orderProcessor.calculateTotal(items(100000, 200000), "SALE10", "NONE", "COD");
        Assert.assertEquals(total, 290000.0, 0.01);
    }

    @Test(description = "MC/DC cho D3: coupon SALE20")
    @Story("MC/DC")
    @Description("Kiem thu nhanh false cua D3 va true cua D4")
    @Severity(SeverityLevel.NORMAL)
    public void testMcdc_D3_Sale20() {
        double total = orderProcessor.calculateTotal(items(100000, 200000), "SALE20", "NONE", "COD");
        Assert.assertEquals(total, 260000.0, 0.01);
    }
}