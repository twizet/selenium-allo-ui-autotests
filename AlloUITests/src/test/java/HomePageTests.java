import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.PrintStream;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HomePageTests {                             // Головний тест_клас
    public static final String GREEN = "\u001b[32m";    // Додаткове оформлення виводу
    public static final String RESET = "\u001b[0m";

    private WebDriver driver;                           // 🔁 "доступний" для всього класу (методів)

    @BeforeMethod                                       // Метод запускається перед кожним тестом
    public void setUp() {
        // Вимикаємо WARN від selenium.devtools за допомогою вбудованого в Java логера
        Logger.getLogger("org.openqa.selenium.devtools").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.chromium").setLevel(Level.SEVERE);

        // Перенаправляємо System.err у "порожній" PrintStream,
        // щоб придушити попередження: "Unable to find CDP implementation matching 138"
        // За замовчуванням System.err — це потік, який виводить помилки в консоль.
        // Створюємо об'єкт типу PrintStream — це клас, що вміє виводити дані (рядки, байти тощо) у потік.
        // PrintStream приймає в конструкторі об'єкт типу OutputStream — куди він буде "писати" дані.
        // new java.io.OutputStream() { ... } — анонімний клас, який наслідується від OutputStream
        // Метод write(int b) в звичайному OutputStream записує байт b у потік.
        // У нашій реалізації write(int b) порожній — тобто байти ігноруються, і нічого не виводиться.
        System.setErr(new PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                // нічого не робимо
            }
        }));

        driver = new ChromeDriver();            // Створення інстансу браузера
        driver.manage().window().maximize();    // Розгортання вікна (аби всі елементи були в доступі)
        driver.get("https://allo.ua/");         // 	Відкриття сайту
    }

    // Методи всі Public! - аби TestNg мав доступ! Під малий проєкт виносити в окремі класи не потрібно. Методами поки.
    @Test
    public void checkLogoIsVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"__layout\"]/div/header/div[1]/div[2]/a/img")
        ));
        Assert.assertTrue(logo.isDisplayed(), "Логотип ALLO не відображається: logo.isDisplayed() == false");
        System.out.println(GREEN + "Тест на наявність лого пройдено успішно!" + RESET);
    }

   /* @Test
    public void searchHairDryerDisplaysRelevantResults() {

    }*/

 /*  @Test
    public void verifyProductNameConsistencyAfterSearch() {

    }*/

 /*   @Test
    public void verifyCustomerMenuNavigationToDeliveryAndPayment() {

    }*/


    @AfterMethod                                // Метод виконується після кожного тесту
    public void turnOff() {
        if (driver != null) {                   // Перевірка, щоб уникнути NullPointerException ( --> тест впав раніше? )
            driver.quit();                      // Закриває всі вікна браузера
        }
    }
}
