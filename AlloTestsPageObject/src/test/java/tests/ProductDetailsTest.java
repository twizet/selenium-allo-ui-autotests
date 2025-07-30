package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchResultsPage;
import utils.Log;

public class ProductDetailsTest extends BaseTest {

    @DataProvider(name = "searchKeywords")
    public Object[][] searchKeywords() {
        return new Object[][]{
                {"Фен"},
                {"Телевізор"},
                {"AirPods 3"}
        };
    }

    @Test(dataProvider = "searchKeywords", priority = 1)
    public void verifyProductNameConsistencyAfterSearch(String keyword) {
        Log.info("Starting ProductDetailsTest: verifyProductNameConsistencyAfterSearch with keyword: " + keyword);

        HomePage homePage = new HomePage(driver);
        homePage.inputSendKeysAndClick(keyword);

        SearchResultsPage searchResults = new SearchResultsPage(driver);
        String firstProductTitle = searchResults.getFirstProductTitleAndTrim().toLowerCase();
        Log.info("SearchPage product title: " + firstProductTitle);

        Assert.assertTrue(firstProductTitle.contains(keyword.toLowerCase()),
                "Перший товар не містить '" + keyword + "'");

        searchResults.clickFirstProduct();

        ProductPage productPage = new ProductPage(driver);
        String productPageTitle = productPage.getProductTitleOnProductPageAndTrim().toLowerCase();
        Log.info("ProductPage product title: " + productPageTitle);

        Assert.assertEquals(productPageTitle, firstProductTitle,
                "Назви товарів на сторінках не співпадають");

        Log.info("ProductDetailsTest passed: Product titles match for keyword: " + keyword);
    }
}
