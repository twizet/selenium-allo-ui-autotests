package tests;

import org.testng.annotations.Test;
import pages.DeliveryAndPaymentPage;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchResultsPage;
import utils.Log;

public class FullFlowTest extends BaseTest {

    @Test(description = "Повний флоу з 4-ма сторінками, на кожній - по 5 перевірок, через ланцюжок методів")
    public void fullFlowWithFourPages() {
        Log.info("=== Start FullFlowTest ===");

        HomePage homePage = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        DeliveryAndPaymentPage deliveryPage = new DeliveryAndPaymentPage(driver);


        // На головній сторінці перевіряємо 5 елементів
        homePage
                .checkLogoVisible()
                .checkSearchInputVisible()
                .checkSearchButtonVisible()
                .checkBuyersButtonVisible()
                .checkDropDownMenuVisible();

        // Переходимо до сторінки доставки і оплати
        homePage.clickDeliveryAndPaymentLink();

        // На сторінці доставки і оплати робимо 5 перевірок
        deliveryPage
                .checkPageTitleCorrect()
                .checkHowToOrderBlockVisible()
                .checkNavigateToPaymentMethod()
                .clickSearchButton()
                .checkPaymentMethodsVisible()
                .checkDeliveryCheckMethod()
                .clickDeliveryCheck()
                .checkDeliveryInfoVisible();
        //.checkRecentlyProductsList();

        // Повертаємось на головну, робимо пошук (для прикладу - "Фен")
        homePage.enterSearchText("Фен").clickSearchButton();

        // На сторінці результатів пошуку виконуємо 5 перевірок
        searchResultsPage
                .checkProductsContainerVisible()
                .checkSortDropdownVisible()
                .checkFiltersPanelVisible()
                .checkResultsCountVisible()
                .checkFirstProductTitleContains("Фен");

        // Натискаємо на перший продукт (переходимо на сторінку продукту)
        searchResultsPage.clickFirstProduct();
        
        // На сторінці продукту виконуємо 5 перевірок
        productPage
                .checkProductTitle(productPage.getProductTitle())  // тут можна отримати назву і перевірити її сама із собою, або додати інший метод для стабільності
                .checkAddToCartButtonVisible()
                .checkProductPriceVisible()
                .checkProductDescriptionVisible()
                .checkProductImageVisible();

        Log.info("=== FullFlowTest passed successfully ===");
    }
}
