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

    public HomePage checkLogoVisible() {
        assert wait.waitForElementToBeVisible(logo).isDisplayed() : "Логотип не видно";
        return this;
    }

    public HomePage checkSearchInputVisible() {
        assert wait.waitForElementToBeVisible(searchInput).isDisplayed() : "Поле пошуку не видно";
        return this;
    }

    public HomePage checkSearchButtonVisible() {
        assert wait.waitForElementToBeVisible(searchButton).isDisplayed() : "Кнопку пошуку не видно";
        return this;
    }

    public HomePage checkBuyersButtonVisible() {
        assert wait.waitForElementToBeVisible(buyersButton).isDisplayed() : "Кнопку 'Покупцям' не видно";
        return this;
    }

    public HomePage checkDropDownMenuVisible() {
        clickBuyersButton(); // клік перед перевіркою випадаючого меню
        assert wait.waitForElementToBeVisible(dropDownMenu).isDisplayed() : "Випадаюче меню не з'явилось";
        return this;
    }

    public HomePage enterSearchText(String query) {
        wait.waitForElementToBeVisible(searchInput).sendKeys(query);
        return this;
    }

    public HomePage clickSearchButton() {
        wait.waitForElementToBeClickable(searchButton).click();
        return this;
    }

    public HomePage clickBuyersButton() {
        wait.waitForElementToBeClickable(buyersButton).click();
        return this;
    }

    public HomePage clickDeliveryAndPaymentLink() {
        wait.waitForElementToBeClickable(deliveryAndPaymentLink).click();
        return this;
    }
}

