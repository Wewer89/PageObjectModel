package reports;

import base_page.BaseCarsPage;
import base_page.BasePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import test_cases.BaseTest;

public class ExtentListener extends BasePage implements ITestListener {

    public ExtentReports extentReports = new ExtentReportManager().createExtentReporter();
    public static ExtentTest extentTest;

    public ExtentListener(WebDriver driver) {
        super(driver);
    }

    @Override
    public void onTestStart(ITestResult result) {
        Object parameters = result.getParameters()[0];
        extentTest = extentReports.createTest("Class name: " + result.getTestClass().getName() + " -> Test name: " +
                result.getMethod().getMethodName() + " -> parameters: " + parameters);

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Markup label = MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.GREEN);
        extentTest.log(Status.PASS, label);
    }

    @Override()
    public void onTestFailure(ITestResult result) {
        Markup label = MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.RED);
        extentTest.log(Status.FAIL, label);
        String errorMessage = result.getThrowable().getMessage();
        extentTest.fail(errorMessage);
        BaseTest.captureScreenShoot(driver);
        //extentTest.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(pathToScreenShoot).build());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Markup label = MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.BLUE);
        extentTest.log(Status.PASS, label);
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}
