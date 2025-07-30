package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

import java.util.List;

import static java.lang.Integer.parseInt;
import static utils.ConfigReader.getProperty;

public class SearchResultsPage {
    private final WaitUtils wait;

    private final By firstProductTitle = By.xpath("//a[@class='product-card__title']");
    private final By productsContainer = By.xpath("//*[@id='__layout']/div/div[1]/div[2]/div/div[2]/div[3]");
    private final By sortDropdown = By.className("sort-by__current");
    private final By filtersPanel = By.className("accordion__body");
    private final By resultsCountLabel = By.className("b-crumbs__link");


    public SearchResultsPage(WebDriver driver) {
        this.wait = new WaitUtils(driver, parseInt(getProperty("timeout")));
    }

    public SearchResultsPage checkProductsContainerVisible() {
        assert wait.waitForElementToBeVisible(productsContainer).isDisplayed() : "Контейнер продуктів не відображається";
        return this;
    }

    public SearchResultsPage checkFirstProductTitleContains(String keyword) {
        String title = wait.waitForElementToBeVisible(firstProductTitle).getText().toLowerCase();
        assert title.contains(keyword.toLowerCase()) : "Перший продукт не містить ключове слово: " + keyword;
        return this;
    }

    public String getFirstProductTitleAndTrim() {
        return wait.waitForElementToBeVisible(firstProductTitle).getText().trim();
    }

    public SearchResultsPage checkSortDropdownVisible() {
        assert wait.waitForElementToBeVisible(sortDropdown).isDisplayed() : "Випадаюче меню сортування не відображається";
        return this;
    }

    public SearchResultsPage checkFiltersPanelVisible() {
        assert wait.waitForElementToBeVisible(filtersPanel).isDisplayed() : "Панель фільтрів не відображається";
        return this;
    }

    public SearchResultsPage checkResultsCountVisible() {
        assert wait.waitForElementToBeVisible(resultsCountLabel).isDisplayed() : "Лейбл з кількістю результатів не відображається";
        return this;
    }

    public void clickFirstProduct() {
        wait.waitForElementToBeClickable(firstProductTitle).click();
    }

    public void checkFirstProductTitleDoesNotContain(List<String> forbiddenWords) {
        String title = wait.waitForElementToBeVisible(firstProductTitle).getText().toLowerCase();
        for (String forbidden : forbiddenWords) {
            assert !title.contains(forbidden.toLowerCase()) : "Назва містить заборонене слово: " + forbidden;
        }
    }
}
