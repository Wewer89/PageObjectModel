package pages;

import base_page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.NewSessionPayload;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//div[contains(text(), 'NEW CARS')]")
    private WebElement newCars;

    @FindBy(xpath = "//div[contains(text(), 'Find New Cars')]")
    private WebElement findNewCars;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public NewCarsPage findNewCar() {
        actions = new Actions(driver);
        actions.moveToElement(newCars).perform();
        findNewCars.click();
        return new NewCarsPage(driver);
    }


}
