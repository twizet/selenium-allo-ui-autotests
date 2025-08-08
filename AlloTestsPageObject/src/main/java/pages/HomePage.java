package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.WaitUtils;

public class HomePage {
    private final WaitUtils wait;

    // Локатори
    private final By logo = By.xpath("//a[@class='v-logo']");
    private final By searchInput = By.xpath("//input[@type='text' and @name='search']");
    private final By searchButton = By.className("search-form__submit-button");

    private final By buyersButton = By.xpath("//*[@id='__layout']/div/header/div[1]/div[1]/div[2]/div");
    private final By dropDownMenu = By.xpath("//*[@id='__layout']/div/header/div[1]/div[1]/div[2]/div/div");
    private final By deliveryAndPaymentLink = By.linkText("Доставка і оплата");

    public HomePage(WebDriver driver) {
        this.wait = new WaitUtils(driver, Integer.parseInt(ConfigReader.getProperty("timeout")));
    }

    public boolean isLogoDisplayed() {
        return wait.waitForElementToBeVisible(logo).isDisplayed();
    }

    public void inputSendKeysAndClick(String query) {
        wait.waitForElementToBeVisible(searchInput).sendKeys(query);
        wait.waitForElementToBeClickable(searchButton).click();
    }

    public void clickBuyersButton() {
        wait.waitForElementToBeClickable(buyersButton).click();
    }

    public boolean isDropDownMenuVisible() {
        return wait.waitForElementToBeVisible(dropDownMenu).isDisplayed();
    }

    public void clickDeliveryAndPaymentLink() {
        wait.waitForElementToBeClickable(deliveryAndPaymentLink).click();
    }
}

