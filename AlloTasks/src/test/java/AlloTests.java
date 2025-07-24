import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v133.tracing.model.TraceConfig;
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


public class AlloTests {
    public static final String GREEN = "\u001b[32m";
    public static final String RESET = "\u001b[0m";
    public static final String RED = "\u001b[31m";
    public static final String BLUE ="\u001b[34m";
    public static final String YELLOW = "\u001b[33m";

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        Logger.getLogger("org.openqa.selenium.devtools").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.chromium").setLevel(Level.SEVERE);

        System.setErr(new PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
            }
        }));

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://allo.ua/");
    }


    @Test
    public void checkLogoIsVisible() throws InterruptedException {
        System.out.println("Test 1:");
        // logo
        Thread.sleep(5000);
        WebElement logo = driver.findElement(
                By.className("v-logo__img"));

        Assert.assertTrue(logo.isDisplayed(),
                RED + "Логотип ALLO не відображається: logo.isDisplayed() == false" + RESET);
        System.out.println(GREEN + "Тест на наявність лого пройдено успішно!" + RESET);
        System.out.println("");
    }

    @Test
    public void searchHairDryerDisplaysRelevantResults() throws InterruptedException {
        System.out.println("Test 2:");
        // inputField
        Thread.sleep(5000);
        WebElement inputField = driver.findElement(By.className("search-form__input"));
        Assert.assertTrue(inputField.isDisplayed(),
                RED + "Поле пошуку не відображається: inputField.isDisplayed() == false" + RESET);
        System.out.println(GREEN + "Тест на наявність поля пошуку пройдено успішно!" + RESET);

        inputField.sendKeys("Фен");
        Thread.sleep(2000);

        // searchButton
        WebElement searchButton = driver.findElement(By.className("search-form__submit-button"));
        Assert.assertTrue(searchButton.isDisplayed(),
                RED + "Кнопка пошуку не відображається: searchButton.isDisplayed() == false" + RESET);
        System.out.println(GREEN + "Тест на наявність кнопки пошуку пройдено успішно!" + RESET);
        searchButton.click();
        Thread.sleep(10000);

        // firstProduct
        WebElement firstProduct = driver.findElement(By.className("product-card__title"));
        Assert.assertTrue(firstProduct.getText().toLowerCase().contains("фен"),
                RED + "Перший товар не містить слово 'Фен'"+ RESET);
        System.out.println(GREEN + "Тест пошуку по слову 'Фен' успішний, перший товар містить це слово!" + RESET);
        System.out.println("");
    }

   @Test
    public void verifyProductNameConsistencyAfterSearch() throws InterruptedException{
       System.out.println("Test 3:");
       // logo
    Thread.sleep(5000);
       WebElement logo = driver.findElement(By.className("v-logo__img"));
       Assert.assertTrue(logo.isDisplayed(),
               RED + "Логотип ALLO не відображається: logo.isDisplayed() == false" + RESET);
       System.out.println(GREEN + "Тест на наявність лого пройдено успішно!" + RESET);

       // inputField
       Thread.sleep(5000);
       WebElement inputField = driver.findElement(By.className("search-form__input"));
       Assert.assertTrue(inputField.isDisplayed(),
               RED + "Поле пошуку не відображається: inputField.isDisplayed() == false" + RESET);
       System.out.println(GREEN + "Тест на наявність поля пошуку пройдено успішно!" + RESET);

       inputField.sendKeys("AirPods 3");
       Thread.sleep(2000);

       // searchButton
       WebElement searchButton = driver.findElement(By.className("search-form__submit-button"));
       Assert.assertTrue(searchButton.isDisplayed(),
               RED + "Кнопка пошуку не відображається: searchButton.isDisplayed() == false" + RESET);
       System.out.println(GREEN + "Тест на наявність кнопки пошуку пройдено успішно!" + RESET);
       searchButton.click();
       Thread.sleep(10000);

       // firstProduct
       WebElement firstProduct = driver.findElement(By.className("product-card__title"));
       String productTitle = firstProduct.getText().toLowerCase();
       Assert.assertTrue(productTitle.contains("airpods 3"),
               RED + "Перший товар не містить 'AirPods 3': " + productTitle + RESET);
       System.out.println(GREEN + "Тест пошуку по запиту 'AirPods 3' успішний, перший товар містить це слово!" + RESET);

       // productTitleOnPage
       System.out.println(GREEN + "Назва першого товару на сторінці пошуку: " + BLUE + productTitle + RESET);
       firstProduct.click();
       Thread.sleep(10000);
       WebElement productTitleOnPage = driver.findElement(By.className("p-view__header-title"));
       Assert.assertTrue(productTitleOnPage.isDisplayed(),
               RED + "Тайтл на сторінці товару відсутній!" + RESET);
       String productTitleOnCurrentPage = productTitleOnPage.getText().toLowerCase();
       Assert.assertEquals(productTitle,productTitleOnCurrentPage,
               "Тайтли під час пошуку першого товару з товаром на сторінці самого товару не співпадають!");
       System.out.println(GREEN + "Тест порівняння тайтлів продукту пройдено успішно:" +
               BLUE + "\n" + productTitle +  GREEN +"\nequals\n" + YELLOW  + productTitleOnCurrentPage + RESET);
       System.out.println("");
    }

    @Test
    public void verifyCustomerMenuNavigationToDeliveryAndPayment() throws InterruptedException{
        System.out.println("Test 4:");
        // buyersButton
        WebElement buyersButton = driver.findElement(By.xpath("//*[@id='__layout']/div/header/div[1]/div[1]/div[2]/div"));
        Assert.assertTrue(buyersButton.isDisplayed(),
                RED + "Кнопку 'Покупцям' не знайдено!" + RESET);
        System.out.println(GREEN + "Тест на знаходження кнопки 'Покупцям' пройдено успішно, але є питання!");
        buyersButton.click();
        Thread.sleep(5000);

        // dropDownMenu
        WebElement dropDownMenu = driver.findElement(By.xpath("//*[@id='__layout']/div/header/div[1]/div[1]/div[2]/div/div"));
        Assert.assertTrue(dropDownMenu.isDisplayed(),
                RED + "Блоку випадаючого меню не знайдено!" + RESET);
        System.out.println(GREEN + "Тест на відкриття випадаючого меню пройдено, але є питання!" + RESET);

        // deliveryAndPaymentLink
        WebElement deliveryAndPaymentLink = driver.findElement(By.linkText("Доставка і оплата"));
        Assert.assertTrue(deliveryAndPaymentLink.isDisplayed(),
                RED + "'Доставка і оплата' не відображається!"+ RESET);
        System.out.println(GREEN + "Тест знаходження 'Доставка і оплата' пройдено успішно!" + RESET);
        deliveryAndPaymentLink.click();
        Thread.sleep(5000);

        // deliveryAndPaymentTitleOnPage
        WebElement deliveryAndPaymentTitleOnPage = driver.findElement(By.cssSelector("h2"));
        Assert.assertEquals(deliveryAndPaymentTitleOnPage.getText().trim(), "Доставка і оплата",
                RED + "Назви тайтлу 'Доставка і оплата' на сторінці не знайдено!" + RESET);
        System.out.println(GREEN + "Тест знаходження тайтлу 'Доставка і оплата' пройдено успішно!" + RESET);

        // howToOrderBlock
        WebElement howToOrderBlock = driver.findElement(By.className("sub-block-header"));
        Assert.assertTrue(howToOrderBlock.isDisplayed(),
                RED + "Блок 'Як оформити замовлення?' не видно!" + RESET);
        Assert.assertEquals(howToOrderBlock.getText(), "Як оформити замовлення?",
                RED +"Текст у блоці не відповідає очікуваному!" + RESET);
        System.out.println(GREEN + "Блок 'Як оформити замовлення?' відображено правильно!" + RESET);
        System.out.println("");
    }


    @AfterMethod
    public void turnOff() {
        if (driver != null) {
            driver.quit();
        }
    }
}