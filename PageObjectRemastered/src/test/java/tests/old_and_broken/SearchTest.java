package tests.old_and_broken;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;
import tests.BaseTest;
import utils.Log;

public class SearchTest extends BaseTest {

    @DataProvider(name = "searchKeywords")
    public static Object[][] searchKeywords() {
        return new Object[][]{
                {"Фен"},
                {"Телевізор"},
                {"AirPods 3"}
        };
    }

    @Test(dataProvider = "searchKeywords", description = "Перевірка, що пошук повертає релевантні результати")
    public void searchDisplaysRelevantResults(String keyword) {
        Log.info("Starting SearchTest with keyword: " + keyword);

        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResults = new SearchResultsPage(driver);

        homePage.enterSearchText(keyword)
                .clickSearchButton();

        searchResults.checkFirstProductTitleContains(keyword);

        Log.info("SearchTest passed: First product contains keyword " + keyword);
    }
}
