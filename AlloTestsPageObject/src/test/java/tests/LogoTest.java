package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.Log;

public class LogoTest extends BaseTest {

    @Test(priority = 1)
    public void checkLogoIsVisible() {
        Log.info("Starting LogoTest: checkLogoIsVisible");

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isLogoDisplayed(), "Логотип ALLO не відображається");

        Log.info("LogoTest passed: Logo is visible");
    }
}
