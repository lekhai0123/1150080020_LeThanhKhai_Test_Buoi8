package tests.bai31;

import org.testng.Assert;
import org.testng.annotations.Test;

public class XepLoaiTest {
    @Test
    public void testInvalidScore() {
        Assert.assertEquals(XepLoai.xepLoai(-1, false), "Diem khong hop le");
    }

    @Test
    public void testGioi() {
        Assert.assertEquals(XepLoai.xepLoai(9.0, false), "Gioi");
    }

    @Test
    public void testKha() {
        Assert.assertEquals(XepLoai.xepLoai(7.5, false), "Kha");
    }

    @Test
    public void testTrungBinh() {
        Assert.assertEquals(XepLoai.xepLoai(6.0, false), "Trung Binh");
    }

    @Test
    public void testThiLai() {
        Assert.assertEquals(XepLoai.xepLoai(4.0, true), "Thi lai");
    }

    @Test
    public void testYeuHocLai() {
        Assert.assertEquals(XepLoai.xepLoai(4.0, false), "Yeu - Hoc lai");
    }
}