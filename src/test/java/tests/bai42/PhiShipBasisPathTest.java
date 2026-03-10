package tests.bai42;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.bai41.PhiShip;

public class PhiShipBasisPathTest {
    @Test(description = "Path 1: Trong luong khong hop le")
    public void testPath1_InvalidWeight() {
        Assert.expectThrows(IllegalArgumentException.class, () -> PhiShip.tinhPhiShip(-1, "noi_thanh", false));
    }

    @Test(description = "Path 2: Noi thanh, <= 5kg, khong member")
    public void testPath2_NoiThanhNheKhongMember() {
        double expected = 15000;
        Assert.assertEquals(PhiShip.tinhPhiShip(3, "noi_thanh", false), expected, 0.01);
    }

    @Test(description = "Path 3: Noi thanh, > 5kg, khong member")
    public void testPath3_NoiThanhNangKhongMember() {
        double expected = 19000;
        Assert.assertEquals(PhiShip.tinhPhiShip(7, "noi_thanh", false), expected, 0.01);
    }

    @Test(description = "Path 4: Ngoai thanh, <= 3kg, khong member")
    public void testPath4_NgoaiThanhNheKhongMember() {
        double expected = 25000;
        Assert.assertEquals(PhiShip.tinhPhiShip(2, "ngoai_thanh", false), expected, 0.01);
    }

    @Test(description = "Path 5: Ngoai thanh, > 3kg, khong member")
    public void testPath5_NgoaiThanhNangKhongMember() {
        double expected = 31000;
        Assert.assertEquals(PhiShip.tinhPhiShip(5, "ngoai_thanh", false), expected, 0.01);
    }

    @Test(description = "Path 6: Tinh khac, <= 2kg, khong member")
    public void testPath6_TinhKhacNheKhongMember() {
        double expected = 50000;
        Assert.assertEquals(PhiShip.tinhPhiShip(1.5, "tinh_khac", false), expected, 0.01);
    }

    @Test(description = "Path 7: Tinh khac, > 2kg, khong member")
    public void testPath7_TinhKhacNangKhongMember() {
        double expected = 60000;
        Assert.assertEquals(PhiShip.tinhPhiShip(4, "tinh_khac", false), expected, 0.01);
    }

    @Test(description = "Path 8: Noi thanh, member duoc giam 10 phan tram")
    public void testPath8_NoiThanhMember() {
        double expected = 13500;
        Assert.assertEquals(PhiShip.tinhPhiShip(3, "noi_thanh", true), expected, 0.01);
    }
}