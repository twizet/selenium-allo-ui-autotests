package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ConfigReader;
import utils.WaitUtils;

public class DeliveryAndPaymentPage {
    private final WaitUtils wait;

    private final By pageTitle = By.cssSelector("h2");
    private final By howToOrderBlock = By.className("sub-block-header");

    public DeliveryAndPaymentPage(WebDriver driver) {
        this.wait = new WaitUtils(driver, Integer.parseInt(ConfigReader.getProperty("timeout")));
    }

    public boolean isPageTitleCorrect() {
        String expectedTitle = "Доставка і оплата";
        return wait.waitForElementToBeVisible(pageTitle).getText().trim().equals(expectedTitle);
    }

    public boolean isHowToOrderBlockVisible() {
        WebElement element = wait.waitForElementToBeVisible(howToOrderBlock);
        return element.isDisplayed() && element.getText().equals("Як оформити замовлення?");
    }
}
