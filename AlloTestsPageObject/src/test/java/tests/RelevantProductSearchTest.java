package tests;

import models.SearchExpectation;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;
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

    @Test(dataProvider = "searchCases", priority = 1)
    public void verifyRelevantSearchResults(SearchExpectation expectation) {
        Log.info("Запуск пошуку: " + expectation.getSearchKeyword());

        HomePage homePage = new HomePage(driver);
        homePage.inputSendKeysAndClick(expectation.getSearchKeyword());

        SearchResultsPage searchPage = new SearchResultsPage(driver);
        String firstProductTitle = searchPage.getFirstProductTitleAndTrim().toLowerCase();

        Log.info("Знайдено товар: " + firstProductTitle);

        Assert.assertTrue(firstProductTitle.contains(expectation.getExpectedInTitle().toLowerCase()),
                "Назва не містить очікуваного: " + expectation.getExpectedInTitle());

        for (String forbidden : expectation.getNotExpectedInTitle()) {
            Assert.assertFalse(firstProductTitle.contains(forbidden.toLowerCase()),
                    "Назва містить заборонене слово: " + forbidden);
        }

        Log.info("Перевірка релевантності пройдена успішно.");
    }
}
