package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DeliveryAndPaymentPage;
import pages.HomePage;
import utils.Log;

public class CustomerMenuTest extends BaseTest {

    @Test(priority = 1)
    public void verifyCustomerMenuNavigationToDeliveryAndPayment() {
        Log.info("Starting CustomerMenuTest: verifyCustomerMenuNavigationToDeliveryAndPayment");

        HomePage homePage = new HomePage(driver);

        homePage.clickBuyersButton();

        Assert.assertTrue(homePage.isDropDownMenuVisible(), "Випадаюче меню 'Покупцям' не відображається");

        homePage.clickDeliveryAndPaymentLink();

        DeliveryAndPaymentPage deliveryPage = new DeliveryAndPaymentPage(driver);

        Assert.assertTrue(deliveryPage.isPageTitleCorrect(), "Заголовок 'Доставка і оплата' не знайдено");
        Assert.assertTrue(deliveryPage.isHowToOrderBlockVisible(), "Блок 'Як оформити замовлення?' відсутній або текст не співпадає");

        Log.info("CustomerMenuTest passed: Navigation to Delivery and Payment page successful");
    }
}
