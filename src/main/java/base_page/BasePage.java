package base_page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    public WebDriver driver;
    public Actions actions;
    public static BaseCarsPage baseCarsPage;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        baseCarsPage = new BaseCarsPage(driver);
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element) {
        element.click();
    }
}
