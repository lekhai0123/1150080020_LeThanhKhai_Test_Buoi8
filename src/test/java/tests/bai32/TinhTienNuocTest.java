package tests.bai32;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TinhTienNuocTest {
    @Test
    public void testSoM3KhongHopLe() {
        Assert.assertEquals(TinhTienNuoc.tinhTienNuoc(0, "dan_cu"), 0.0);
    }

    @Test
    public void testHoNgheo() {
        Assert.assertEquals(TinhTienNuoc.tinhTienNuoc(5, "ho_ngheo"), 25000.0);
    }

    @Test
    public void testDanCuBac1() {
        Assert.assertEquals(TinhTienNuoc.tinhTienNuoc(8, "dan_cu"), 60000.0);
    }

    @Test
    public void testDanCuBac2() {
        Assert.assertEquals(TinhTienNuoc.tinhTienNuoc(15, "dan_cu"), 148500.0);
    }

    @Test
    public void testDanCuBac3() {
        Assert.assertEquals(TinhTienNuoc.tinhTienNuoc(25, "dan_cu"), 285000.0);
    }

    @Test
    public void testKinhDoanh() {
        Assert.assertEquals(TinhTienNuoc.tinhTienNuoc(10, "kinh_doanh"), 220000.0);
    }
}