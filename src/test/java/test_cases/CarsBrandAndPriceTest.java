package test_cases;

import base_page.BasePage;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import pages.HomePage;

import java.io.IOException;
import java.util.Hashtable;

public class CarsBrandAndPriceTest extends BaseTest {

    @Test(dataProvider = "getData")
    public void carsBrandAndPriceTest(Hashtable<String, String> data) throws IOException {
        if (data.get("runmode").equalsIgnoreCase("Y")) {
            setUp(data.get("browser"));
            String brandName = data.get("brandName");
            if (brandName.equalsIgnoreCase("bmw")) {
                HomePage homePage = new HomePage(driver);
                homePage.findNewCar().selectBmwCar();
                BasePage.baseCarsPage.createCarsBrandAndPriceList();
                BasePage.baseCarsPage.writeCarBrandsAndPricesInFile();
                Assert.fail("FAILED TEST!!!");
            } else if (brandName.equalsIgnoreCase("toyota")) {
                HomePage homePage = new HomePage(driver);
                homePage.findNewCar().selectToyotaCar();
                BasePage.baseCarsPage.createCarsBrandAndPriceList();
                BasePage.baseCarsPage.writeCarBrandsAndPricesInFile();
            } else if (brandName.equalsIgnoreCase("audi")) {
                HomePage homePage = new HomePage(driver);
                homePage.findNewCar().selectAudiCar();
                BasePage.baseCarsPage.createCarsBrandAndPriceList();
                BasePage.baseCarsPage.writeCarBrandsAndPricesInFile();
            } else {
                throw new SkipException("Runmode is set up for 'N' for this test case");
            }
        }
    }
}