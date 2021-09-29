package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.util.Date;

public class ExtentReportManager {

    public ExtentReports createExtentReporter() {
        Date date = new Date();
        String fileName = date.toString().replace(":", "_").replace(" ", "_") + ".html";

        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("target\\reports\\" + fileName);
        extentSparkReporter.config().setDocumentTitle("Page Object Model");
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setEncoding("utf-8");
        extentSparkReporter.config().setReportName("Page Object Model Report");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Tester: ", "Michal Wewerek");
        extentReports.setSystemInfo("Company: ", "Craftware");

        return extentReports;
    }
}
