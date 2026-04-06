package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.PaymentPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MtsOnlinePaymentTest {
    private WebDriver driver;
    private PaymentPage paymentPage;
    private WebDriverWait wait;

    @BeforeAll
    static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://www.mts.by");
        paymentPage = new PaymentPage(driver);
        paymentPage.closeCookieBanner();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testPaymentLogosPresence() {
        assertTrue(paymentPage.arePaymentLogosDisplayed(), "Не все платёжные логотипы отображаются");
    }

    @Test
    void testCommunicationServicePayment() {
        String phoneNumber = "297777777";
        String sum = "5";

        paymentPage.selectService("Услуги связи")
                .fillPhone(phoneNumber)
                .fillSum(sum)
                .fillEmail("test@example.com")
                .clickContinue();
        try { Thread.sleep(5000); } catch (InterruptedException e) {}
        List<WebElement> sumHeaders = driver.findElements(By.xpath("//*[contains(text(), '5.00') or contains(text(), 'BYN')]"));
        assertFalse(sumHeaders.isEmpty(), "Сумма не отображается в заголовке");
        List<WebElement> phoneDisplays = driver.findElements(By.xpath("//*[contains(text(), '375297777777') or contains(text(), '297777777')]"));
        assertFalse(phoneDisplays.isEmpty(), "Номер телефона не отображается");
        List<WebElement> payButtons = driver.findElements(By.xpath("//button[contains(text(), '5') or contains(text(), 'Оплатить')]"));
        assertFalse(payButtons.isEmpty(), "Кнопка оплаты не найдена");
        assertTrue(payButtons.get(0).getText().contains(sum), "На кнопке оплаты неверная сумма");
        List<WebElement> cardNumberFields = driver.findElements(By.xpath("//input[contains(@placeholder, 'Номер') or contains(@placeholder, 'номер')]"));
        assertFalse(cardNumberFields.isEmpty(), "Поле 'Номер карты' не найдено");
        List<WebElement> expiryFields = driver.findElements(By.xpath("//input[contains(@placeholder, 'Срок') or contains(@placeholder, 'срок')]"));
        assertFalse(expiryFields.isEmpty(), "Поле 'Срок действия' не найдено");
        List<WebElement> cvvFields = driver.findElements(By.xpath("//input[contains(@placeholder, 'CVC') or contains(@placeholder, 'cvc') or contains(@placeholder, 'CVV')]"));
        assertFalse(cvvFields.isEmpty(), "Поле 'CVC' не найдено");
        List<WebElement> nameFields = driver.findElements(By.xpath("//input[contains(@placeholder, 'Имя') or contains(@placeholder, 'имя') or contains(@placeholder, 'Name')]"));
        assertFalse(nameFields.isEmpty(), "Поле 'Имя и фамилия на карте' не найдено");
        List<WebElement> visaIcon = driver.findElements(By.xpath("//img[contains(@src, 'visa')]"));
        List<WebElement> mcIcon = driver.findElements(By.xpath("//img[contains(@src, 'mastercard')]"));

        assertTrue(visaIcon.size() > 0, "Иконка Visa не найдена");
        assertTrue(mcIcon.size() > 0, "Иконка Mastercard не найдена");

        System.out.println("Все проверки модального окна пройдены успешно!");
    }
}