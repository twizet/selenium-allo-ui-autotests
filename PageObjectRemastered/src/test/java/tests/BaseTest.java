package tests;

import config.SingletonThreadSafeDriver;
import listeners.TestDescriptionLoggerListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.ConfigReader;
import utils.Log;

import java.time.Duration;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

@Listeners({TestDescriptionLoggerListener.class})
public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // 🔇 Вимкнення лише логів Selenium DevTools
        Logger seleniumLogger = Logger.getLogger("org.openqa.selenium.devtools");
        seleniumLogger.setLevel(Level.OFF);
        for (var handler : seleniumLogger.getHandlers()) {
            if (handler instanceof ConsoleHandler) {
                handler.setLevel(Level.OFF);
            }
        }

        Log.info("=== Test Started ===");

        int timeout = Integer.parseInt(ConfigReader.getProperty("timeout"));

        driver = SingletonThreadSafeDriver.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        driver.manage().window().maximize();

        String baseUrl = ConfigReader.getProperty("baseUrl");
        driver.get(baseUrl);

        Log.info("Navigated to: " + baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Log.info("=== Test Finished ===");

        SingletonThreadSafeDriver.quitDriver();
    }
}
