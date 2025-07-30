package tests.old_and_broken;

import org.testng.annotations.Test;
import pages.HomePage;
import tests.BaseTest;
import utils.Log;

public class LogoTest extends BaseTest {

    @Test(description = "Перевірка, що логотип ALLO відображається на головній сторінці")
    public void checkLogoIsVisible() {
        Log.info("Starting LogoTest: checkLogoIsVisible");

        HomePage homePage = new HomePage(driver);

        homePage.checkLogoVisible();

        Log.info("LogoTest passed: Logo is visible");
    }
}
