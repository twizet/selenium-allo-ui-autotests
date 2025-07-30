package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.WaitUtils;

public class DeliveryAndPaymentPage {
    private final WaitUtils wait;

    private final By pageTitle = By.cssSelector("h2");
    private final By howToOrderBlock = By.className("sub-block-header");
    private final By paymentMethodsBlock = By.xpath("//div[contains(@class,'sp-wrapper-block')]//h3[contains(text(),'Якими способами можна оплатити товар?')]");
    private final By paymentButton = By.xpath("//*[@id='__layout']/div/div[1]/div[2]/div/div/div/div[2]/div[1]/button[5]");
    //"div[@class='sp-wrapper-block']//descendant::h3[contains(text(),'Якими способами можна оплатити товар?')]"
    private final By deliveryButton = By.xpath("//*[@id='pleaseOpenCheck']");
    private final By deliveryInfoBlock = By.xpath(
            "//div[@class='sp-wrapper-block']//descendant::h3[contains(text(),'Перевірка відправлень')]");
    //private final By listSection = By.className(".recently-products__list.v-ps");


    public DeliveryAndPaymentPage(WebDriver driver) {
        this.wait = new WaitUtils(driver, Integer.parseInt(ConfigReader.getProperty("timeout")));
    }

    public DeliveryAndPaymentPage checkPageTitleCorrect() {
        String expectedTitle = "Доставка і оплата";
        String actualTitle = wait.waitForElementToBeVisible(pageTitle).getText().trim();
        assert actualTitle.equals(expectedTitle) : "Заголовок сторінки очікувався '" + expectedTitle + "', але отримано '" + actualTitle + "'";
        return this;
    }

    public DeliveryAndPaymentPage checkHowToOrderBlockVisible() {
        String expectedText = "Як оформити замовлення?";
        String actualText = wait.waitForElementToBeVisible(howToOrderBlock).getText().trim();
        assert actualText.equals(expectedText) : "Текст блоку очікувався '" + expectedText + "', але отримано '" + actualText + "'";
        return this;
    }

    public DeliveryAndPaymentPage checkPaymentMethodsVisible() {
        assert wait.waitForElementToBeVisible(paymentMethodsBlock).isDisplayed() : "Блок методів оплати не відображається";
        return this;
    }

    public DeliveryAndPaymentPage checkDeliveryInfoVisible() {
        assert wait.waitForElementToBeVisible(deliveryInfoBlock).isDisplayed() : "Блок інформації про доставку не відображається";
        return this;
    }


/*    public DeliveryAndPaymentPage checkRecentlyProductsList() {
        assert wait.waitForElementToBeVisible(listSection).isDisplayed() : "Блок Ви дивилися не відображається";
        return this;
    }*/

    public DeliveryAndPaymentPage checkNavigateToPaymentMethod() {
        assert wait.waitForElementToBeClickable(paymentButton).isDisplayed();
        return this;
    }

    public DeliveryAndPaymentPage clickSearchButton() {
        wait.waitForElementToBeClickable(paymentButton).click();
        return this;
    }

    public DeliveryAndPaymentPage checkDeliveryCheckMethod() {
        assert wait.waitForElementToBeClickable(deliveryButton).isDisplayed();
        return this;
    }

    public DeliveryAndPaymentPage clickDeliveryCheck() {
        wait.waitForElementToBeClickable(deliveryButton).click();
        return this;
    }

}

