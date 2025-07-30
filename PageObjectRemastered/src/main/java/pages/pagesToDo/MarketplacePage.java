package pages.pagesToDo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

import static java.lang.Integer.parseInt;
import static utils.ConfigReader.getProperty;

// https://allo.ua/ua/marketplace-allo/

public class MarketplacePage {

    private final WaitUtils wait;

    private final By youTubePlayButton = By.xpath("//button[@aria-label='Play']");
    private final By toBePartnerButton = By.className("new-img-btn");

    public MarketplacePage(WebDriver driver) {
        this.wait = new WaitUtils(driver, parseInt(getProperty("timeout")));
    }

    // 1
    public boolean isWebPageCorrect(String url) {
        return wait.waitForUrlToContain(url);
    }

    //2
    public boolean isYouTubeButtonDisplayed() {
        return wait.waitForElementToBeVisible(youTubePlayButton).isDisplayed();
    }

    //3
    public void isYouTubeButtonClickable() {
        wait.waitForElementToBeClickable(youTubePlayButton).click();
    }

    //4
    public boolean istoBePartnerButtonDisplayed() {
        return wait.waitForElementToBeVisible(toBePartnerButton).isDisplayed();
    }

    //5
    public void istoBePartnerButtonClickable() {
        wait.waitForElementToBeClickable(toBePartnerButton).click();
    }
}
