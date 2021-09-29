package pages;

import base_page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewCarsPage extends BasePage {

    @FindBy(xpath = "//img[@title='BMW']")
    private WebElement bmw;

    @FindBy(xpath = "//img[@title='Toyota']")
    private WebElement toyota;

    @FindBy(xpath= "//img[@title='Audi']")
    private WebElement audi;

    public NewCarsPage(WebDriver driver) {
        super(driver);
    }

    public void selectBmwCar() {
        bmw.click();
    }

    public void selectToyotaCar() {
        toyota.click();
    }

    public void selectAudiCar() {
        audi.click();
    }

}
