package tests.old_and_broken;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchResultsPage;
import tests.BaseTest;
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

    @Test(dataProvider = "searchKeywords", description = "Перевірка відповідності назви товару після пошуку та на сторінці продукту")
    public void verifyProductNameConsistencyAfterSearch(String keyword) {
        Log.info("Starting ProductDetailsTest with keyword: " + keyword);

        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResults = new SearchResultsPage(driver);
        ProductPage productPage = new ProductPage(driver);

        // Виконуємо пошук
        homePage.enterSearchText(keyword)
                .clickSearchButton();

        // Перевірка, що перший товар містить keyword
        searchResults.checkFirstProductTitleContains(keyword);

        Log.info("First product title contains keyword: " + keyword);

        // Переходимо до сторінки продукту
        searchResults.clickFirstProduct();

        // Перевірка, що заголовок товару співпадає з назвою з результатів пошуку
        String firstProductTitle = searchResults.getFirstProductTitleAndTrim();
        productPage.checkProductTitle(firstProductTitle);

        Log.info("Product title on product page matches the first product title in search results");

        Log.info("ProductDetailsTest passed for keyword: " + keyword);
    }
}
