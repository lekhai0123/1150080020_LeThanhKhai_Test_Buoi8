package tests.bai71;

import io.qameta.allure.Attachment;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureTestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        attachText("Failed test: " + result.getName());
    }

    @Attachment(value = "Failure Detail", type = "text/plain")
    public String attachText(String message) {
        return message;
    }
}