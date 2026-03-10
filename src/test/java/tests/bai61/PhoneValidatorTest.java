package tests.bai61;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PhoneValidatorTest {
    @Test(description = "Path 1: null")
    public void testPath1_NullPhone() {
        Assert.assertFalse(PhoneValidator.isValid(null), "null phai khong hop le");
    }

    @Test(description = "Path 2: chua ky tu khong hop le")
    public void testPath2_InvalidCharacter() {
        Assert.assertFalse(PhoneValidator.isValid("09a1234567"), "So co ky tu chu phai khong hop le");
    }

    @Test(description = "Path 3: khong bat dau bang 0 hoac +84")
    public void testPath3_InvalidPrefix() {
        Assert.assertFalse(PhoneValidator.isValid("84123456789"), "So khong bat dau bang 0 hoac +84 phai khong hop le");
    }

    @Test(description = "Path 4: bat dau bang +84 va hop le")
    public void testPath4_ValidPlus84() {
        Assert.assertTrue(PhoneValidator.isValid("+84912345678"), "So +84 hop le phai duoc chap nhan");
    }

    @Test(description = "Path 5: do dai sau chuan hoa khong bang 10")
    public void testPath5_InvalidLength() {
        Assert.assertFalse(PhoneValidator.isValid("091234567"), "So co do dai sai phai khong hop le");
    }

    @Test(description = "Path 6: co khoang trang hop le")
    public void testPath6_ValidWithSpaces() {
        Assert.assertTrue(PhoneValidator.isValid("09123 45678"), "So co khoang trang hop le phai duoc chap nhan");
    }

    @Test(description = "Path 7: dau so khong thuoc 03 05 07 08 09")
    public void testPath7_InvalidNetworkPrefix() {
        Assert.assertFalse(PhoneValidator.isValid("0212345678"), "Dau so 02 phai khong hop le");
    }

    @Test(description = "Path 8: so hop le dang 0xxxxxxxxx")
    public void testPath8_ValidLocalPhone() {
        Assert.assertTrue(PhoneValidator.isValid("0912345678"), "So hop le dang 0xxxxxxxxx phai duoc chap nhan");
    }

    @Test(description = "Boundary: rong")
    public void testBoundary_Empty() {
        Assert.assertFalse(PhoneValidator.isValid(""), "Chuoi rong phai khong hop le");
    }

    @Test(description = "Boundary: chi co khoang trang")
    public void testBoundary_OnlySpaces() {
        Assert.assertFalse(PhoneValidator.isValid("   "), "Chuoi chi co khoang trang phai khong hop le");
    }

    @Test(description = "Boundary: dau 03 hop le")
    public void testBoundary_03Prefix() {
        Assert.assertTrue(PhoneValidator.isValid("0312345678"), "Dau 03 phai hop le");
    }

    @Test(description = "Boundary: dau 05 hop le")
    public void testBoundary_05Prefix() {
        Assert.assertTrue(PhoneValidator.isValid("0512345678"), "Dau 05 phai hop le");
    }

    @Test(description = "Boundary: dau 07 hop le")
    public void testBoundary_07Prefix() {
        Assert.assertTrue(PhoneValidator.isValid("0712345678"), "Dau 07 phai hop le");
    }

    @Test(description = "Boundary: dau 08 hop le")
    public void testBoundary_08Prefix() {
        Assert.assertTrue(PhoneValidator.isValid("0812345678"), "Dau 08 phai hop le");
    }

    @Test(description = "Boundary: +84 co khoang trang hop le")
    public void testBoundary_Plus84WithSpaces() {
        Assert.assertTrue(PhoneValidator.isValid("+84 912 345 678"), "So +84 co khoang trang phai hop le");
    }

    @Test(description = "Boundary: qua 10 chu so")
    public void testBoundary_TooLong() {
        Assert.assertFalse(PhoneValidator.isValid("09123456789"), "So qua 10 chu so phai khong hop le");
    }

    @Test(description = "Boundary: +84 nhung thieu chu so")
    public void testBoundary_Plus84TooShort() {
        Assert.assertFalse(PhoneValidator.isValid("+8412345678"), "So +84 thieu chu so phai khong hop le");
    }
}