package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;
import utils.Log;

public class SearchTest extends BaseTest {

    @DataProvider
    public static Object[][] searchKeywords() {
        return new Object[][]{
                {"Фен"},
                {"Телевізор"},
                {"AirPods 3"}
        };
    }

    @Test(dataProvider = "searchKeywords", priority = 1)
    public void searchHairDryerDisplaysRelevantResults(String keyword) throws InterruptedException {
        Log.info("Starting SearchTest: searchHairDryerDisplaysRelevantResults");

        HomePage homePage = new HomePage(driver);
        homePage.inputSendKeysAndClick(keyword);

        SearchResultsPage searchResults = new SearchResultsPage(driver);
        String firstProduct = searchResults.getFirstProductTitleAndTrim().toLowerCase();
        Log.info("Current product title (toLower) is: " + firstProduct);

        Assert.assertTrue(firstProduct.contains(keyword.toLowerCase()), "Перший товар не містить '" + keyword + "'");

        Log.info("SearchTest passed: First product contains keyword " + keyword);
    }
}
