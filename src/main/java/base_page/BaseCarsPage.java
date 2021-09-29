package base_page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseCarsPage {

    public WebDriver driver;
    public FileWriter fileWriter;

    public BaseCarsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[contains(text(), 'Cars')]")
    public WebElement carTitle;

    public String getCarTitle() {
        return carTitle.getText();
    }

    @FindBy(xpath = "//li/div/div/div/a/h3")
    public List<WebElement> carsBrand;

    @FindBy(xpath = "//li/div/div/div/div[2]/span[1]")
    public List<WebElement> carsPrices;

    List<String> carsBrandAndPriceList = new ArrayList<>();

    public void createCarsBrandAndPriceList() {
        for (int i = 0; i < carsPrices.size(); i++) {
            String carBrand = carsBrand.get(i).getText();
            String carPrice = carsPrices.get(i).getText();
            carsBrandAndPriceList.add("Car brand: " + carBrand + ", Car price: " + carPrice);
        }
    }

    public void writeCarBrandsAndPricesInFile() throws IOException {
        Date date = new Date();
        String dataStamp = date.toString().replace(":", "_").replace(" ", "_") + ".txt";

        String path = "src\\test\\java\\cars_brands_and_prices\\" + dataStamp;
        try {
            fileWriter = new FileWriter(path);
            for (String singleLine : carsBrandAndPriceList) {
                fileWriter.append(singleLine).append("\n");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            fileWriter.close();
        }

    }
}
