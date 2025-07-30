package utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
    private final WebDriverWait wait;

    public WaitUtils(WebDriver driver, int timeouts) {
        // driver потрібен у WaitUtils, бо саме він виконує пошук елементів і є "точкою входу" для WebDriverWait.
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeouts)); // можна винести в конфіг
    }

    // Очікує, поки елемент стане клікабельним (тобто є у DOM і готовий до кліку)
    public WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Очікує, поки елемент з'явиться на сторінці (не тільки в DOM, а реально видно користувачу)
    public WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Очікує, поки елемент pop-up зникне (невидимий або відсутній)
    public boolean waitForElementToBeInvisible(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // Очікує наявність тексту в елементі
    public boolean waitForTextToBePresentInElement(By locator, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    //Чекає, поки з'явиться JavaScript-алерт (випливне вікно з alert()).)
    public Alert waitForAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    // Очікує оновлення URL (наприклад, після переходу)
    public boolean waitForUrlToContain(String partialUrl) {
        return wait.until(ExpectedConditions.urlContains(partialUrl));
    }
}
