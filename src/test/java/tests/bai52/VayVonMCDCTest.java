package tests.bai52;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.bai51.VayVon;

public class VayVonMCDCTest {
    @Test(description = "MC/DC cho A: tuoi >= 22, truong hop co du dieu kien vay")
    public void testMCDC_TuoiDocLap_Du22TroLen() {
        boolean actual = VayVon.duDieuKienVay(25, 12000000, true, 650);
        Assert.assertTrue(actual, "Khach hang phai du dieu kien vay");
    }

    @Test(description = "MC/DC cho A: tuoi < 22 lam thay doi ket qua")
    public void testMCDC_TuoiDocLap_ThapHon22() {
        boolean actual = VayVon.duDieuKienVay(20, 12000000, true, 650);
        Assert.assertFalse(actual, "Khach hang khong du dieu kien vay khi tuoi < 22");
    }

    @Test(description = "MC/DC cho B: thu nhap < 10 trieu lam thay doi ket qua")
    public void testMCDC_ThuNhapDocLap_ThapHon10Trieu() {
        boolean actual = VayVon.duDieuKienVay(25, 8000000, true, 650);
        Assert.assertFalse(actual, "Khach hang khong du dieu kien vay khi thu nhap < 10 trieu");
    }

    @Test(description = "MC/DC cho C: khong co tai san bao lanh va diem tin dung thap")
    public void testMCDC_TaiSanDocLap_KhongCoTaiSan() {
        boolean actual = VayVon.duDieuKienVay(25, 12000000, false, 650);
        Assert.assertFalse(actual, "Khach hang khong du dieu kien vay khi khong co tai san bao lanh va diem tin dung thap");
    }

    @Test(description = "MC/DC cho D: diem tin dung >= 700 lam thay doi ket qua")
    public void testMCDC_DiemTinDungDocLap_Tu700TroLen() {
        boolean actual = VayVon.duDieuKienVay(25, 12000000, false, 750);
        Assert.assertTrue(actual, "Khach hang phai du dieu kien vay khi diem tin dung >= 700");
    }
}