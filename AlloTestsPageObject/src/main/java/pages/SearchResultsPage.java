package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.WaitUtils;

public class SearchResultsPage {
    private final WaitUtils wait;

    //private final By firstProductTitle = By.xpath("//div[@class='products-layout__container products-layout--grid']/descendant::a[1]");
    private final By firstProductTitle = By.xpath("//a[@class='product-card__title']");

    public SearchResultsPage(WebDriver driver) {
        this.wait = new WaitUtils(driver, Integer.parseInt(ConfigReader.getProperty("timeout")));
    }

    public String getFirstProductTitleAndTrim() {
        return wait.waitForElementToBeVisible(firstProductTitle).getText().trim();
    }

    public void clickFirstProduct() {
        wait.waitForElementToBeClickable(firstProductTitle).click();
    }
}
