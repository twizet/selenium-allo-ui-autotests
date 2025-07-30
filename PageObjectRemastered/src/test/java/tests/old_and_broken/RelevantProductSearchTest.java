package tests.old_and_broken;

import models.SearchExpectation;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;
import tests.BaseTest;
import utils.Log;

import java.util.List;

public class RelevantProductSearchTest extends BaseTest {

    @DataProvider(name = "searchCases")
    public Object[][] searchCases() {
        return new Object[][]{
                {
                        new SearchExpectation.Builder()
                                .searchKeyword("AirPods 3")
                                .expectedInTitle("AirPods 3")
                                .notExpectedInTitle(List.of("чохол", "кейс", "кейc"))
                                .build()
                },
                {
                        new SearchExpectation.Builder()
                                .searchKeyword("iPhone 15")
                                .expectedInTitle("iPhone 15")
                                .notExpectedInTitle(List.of("чохол", "аксесуар"))
                                .build()
                }
        };
    }

    @Test(dataProvider = "searchCases", description = "Перевірка релевантності пошукових результатів")
    public void verifyRelevantSearchResults(SearchExpectation expectation) {
        Log.info("Запуск пошуку: " + expectation.getSearchKeyword());

        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchPage = new SearchResultsPage(driver);

        homePage.enterSearchText(expectation.getSearchKeyword())
                .clickSearchButton();

        searchPage.checkFirstProductTitleContains(expectation.getExpectedInTitle())
                .checkFirstProductTitleDoesNotContain(expectation.getNotExpectedInTitle());

        Log.info("Перевірка релевантності пройдена успішно.");
    }
}
