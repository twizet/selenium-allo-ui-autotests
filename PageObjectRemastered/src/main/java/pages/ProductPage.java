package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.WaitUtils;

public class ProductPage {
    private final WaitUtils wait;

    private final By productTitle = By.className("p-view__header-title");
    private final By addToCartButton = By.xpath("//*[@id='product-buy-button']");
    private final By productPrice = By.xpath(
            "//*[@id='js-p-view']/div[2]/main/div[5]/div[1]/div/div[1]/div[2]/span");
    private final By productDescription = By.xpath("//*[@id='extended-description']/div/div");
    private final By productImage = By.xpath(
            "//*[@id='js-p-view']/div[2]/main/div[1]/div[1]/div[2]/div/div[1]/div[1]/picture/img"); // замінити локатор під сайт

    public ProductPage(WebDriver driver) {
        this.wait = new WaitUtils(driver, Integer.parseInt(ConfigReader.getProperty("timeout")));
    }

    // Метод для отримання заголовку продукту
    public String getProductTitle() {
        return wait.waitForElementToBeVisible(productTitle).getText().trim();
    }

    // Перевірка, що заголовок продукту співпадає з очікуваним
    public ProductPage checkProductTitle(String expectedTitle) {
        String actualTitle = getProductTitle();
        assert actualTitle.equals(expectedTitle) : "Очікуваний заголовок: '" + expectedTitle + "', отримано: '" + actualTitle + "'";
        return this;
    }

    public ProductPage checkAddToCartButtonVisible() {
        assert wait.waitForElementToBeVisible(addToCartButton).isDisplayed() : "Кнопка 'Додати в кошик' не відображається";
        return this;
    }

    public ProductPage checkProductPriceVisible() {
        assert wait.waitForElementToBeVisible(productPrice).isDisplayed() : "Ціна продукту не відображається";
        return this;
    }

    public ProductPage checkProductDescriptionVisible() {
        assert wait.waitForElementToBeVisible(productDescription).isDisplayed() : "Опис продукту не відображається";
        return this;
    }

    public void checkProductImageVisible() {
        assert wait.waitForElementToBeVisible(productImage).isDisplayed() : "Фото продукту не відображається";
    }
}
