package test_cases;

import excel.ExcelManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import reports.ExtentReportManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    public Properties config;
    public ExtentReportManager extentReportManager;

    public void setUp(String browser) throws IOException {

        config = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/test/java/properties/config.properties");
        config.load(fileInputStream);
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--disable-notifications");
            driver = new FirefoxDriver(options);
        }
        driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getProperty("wait")), TimeUnit.SECONDS);
        driver.get(config.getProperty("url"));
        extentReportManager = new ExtentReportManager();
    }

    @DataProvider
    public Object[][] getData(Method method) throws IOException {
        List<Hashtable> tablesWithData = ExcelManager.getDataFromExcel(method.getName(), "src\\test\\java\\excel\\pom.xlsx");
        Object[][] data = new Object[tablesWithData.size()][1];
        for (int row = 0; row < tablesWithData.size(); row++) {
            data[row][0] = tablesWithData.get(row);
        }
        return data;
    }

    @AfterMethod
    public void tearDown() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
