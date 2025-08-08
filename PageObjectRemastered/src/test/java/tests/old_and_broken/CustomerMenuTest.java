package tests.old_and_broken;

import org.testng.annotations.Test;
import pages.DeliveryAndPaymentPage;
import pages.HomePage;
import tests.BaseTest;
import utils.Log;

public class CustomerMenuTest extends BaseTest {

    @Test(description = "Перевірка переходу через меню 'Покупцям' до сторінки 'Доставка і оплата'")
    public void verifyCustomerMenuNavigationToDeliveryAndPayment() {
        Log.info("Starting CustomerMenuTest: verifyCustomerMenuNavigationToDeliveryAndPayment");

        HomePage homePage = new HomePage(driver);
        DeliveryAndPaymentPage deliveryPage = new DeliveryAndPaymentPage(driver);

        homePage
                .clickBuyersButton()
                .checkDropDownMenuVisible()
                .clickDeliveryAndPaymentLink();

        deliveryPage
                .checkPageTitleCorrect()
                .checkHowToOrderBlockVisible();

        Log.info("CustomerMenuTest passed: Navigation to Delivery and Payment page successful");
    }
}
