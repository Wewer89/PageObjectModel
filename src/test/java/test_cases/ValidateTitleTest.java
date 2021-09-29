package test_cases;

import base_page.BasePage;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import pages.HomePage;

import java.io.IOException;
import java.util.Hashtable;

public class ValidateTitleTest extends BaseTest {

    @Test(dataProvider = "getData")
    public void validateTitleTest(Hashtable<String, String> data) throws IOException {
        if (data.get("runmode").equalsIgnoreCase("Y")) {
            setUp(data.get("browser"));
            if (data.get("brandName").equalsIgnoreCase("bmw")) {
                HomePage homePage = new HomePage(driver);
                homePage.findNewCar().selectBmwCar();
                String expectedResult = "BMW Cars";
                String actualResult = BasePage.baseCarsPage.getCarTitle();
                Assert.assertEquals(actualResult, expectedResult);
            } else if (data.get("brandName").equalsIgnoreCase("toyota")) {
                HomePage homePage = new HomePage(driver);
                homePage.findNewCar().selectToyotaCar();
                String expectedResult = "Toyota Cars";
                String actualResult = BasePage.baseCarsPage.getCarTitle();
                Assert.assertEquals(actualResult, expectedResult);
            } else if (data.get("brandName").equalsIgnoreCase("audi")) {
                HomePage homePage = new HomePage(driver);
                homePage.findNewCar().selectAudiCar();
                String expectedResult = "Audi Car";
                String actualResult = BasePage.baseCarsPage.getCarTitle();
                Assert.assertEquals(actualResult, expectedResult);
            } else {
                throw new SkipException("Runmode is set up for 'N' for this test case");
            }
        }
    }
}