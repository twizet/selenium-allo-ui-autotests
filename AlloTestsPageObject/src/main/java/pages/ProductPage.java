package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.WaitUtils;

public class ProductPage {
    private final WaitUtils wait;

    private final By productTitle = By.className("p-view__header-title");

    public ProductPage(WebDriver driver) {
        this.wait = new WaitUtils(driver, Integer.parseInt(ConfigReader.getProperty("timeout")));
    }

    public String getProductTitleOnProductPageAndTrim() {
        return wait.waitForElementToBeVisible(productTitle).getText().trim();
    }
}
