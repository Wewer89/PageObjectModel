package reports;

import base_page.BaseCarsPage;
import base_page.BasePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import test_cases.BaseTest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;

public class ExtentListener implements ITestListener {

    public ExtentReports extentReports = new ExtentReportManager().createExtentReporter();
    public static ExtentTest extentTest;
    public static String pathToScreenShoot;

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
        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        extentTest.fail(exceptionMessage);
        captureScreenShoot(BasePage.driver);
        extentTest.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(pathToScreenShoot).build());
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

    public static void captureScreenShoot(WebDriver driver) {
        Date date = new Date();
        String fileName = date.toString().replace(" ", "_").replace(":", "_") + "jpg";

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        pathToScreenShoot = "target\\extent-reports\\" + fileName;  // Remember to create path to target folder
        try {
            FileUtils.copyFile(screenshot, new File(pathToScreenShoot));  // move file to particular location
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}