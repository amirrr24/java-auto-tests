package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.example.pages.PaymentPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Тестирование сайта MTS")
@Feature("Онлайн пополнение без комиссии")
public class MtsOnlinePaymentTest {
    private WebDriver driver;
    private PaymentPage paymentPage;

    @BeforeAll
    static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
    @Story("Проверка наличия логотипов платёжных систем")
    @Description("Тест проверяет, что на главной странице отображаются все логотипы платёжных систем")
    @Severity(SeverityLevel.NORMAL)
    void testPaymentLogosPresence() {
        assertTrue(paymentPage.arePaymentLogosDisplayed(), "Не все платёжные логотипы отображаются");
    }

    @Test
    @Story("Оплата услуг связи")
    @Description("Тест заполняет форму оплаты услуг связи и проверяет корректность отображения данных")
    @Severity(SeverityLevel.CRITICAL)
    void testCommunicationServicePayment() {
        String phoneNumber = "297777777";
        String sum = "5";

        paymentPage.selectService("Услуги связи")
                .fillPhone(phoneNumber)
                .fillSum(sum)
                .fillEmail("test@example.com")
                .clickContinue();

        assertTrue(paymentPage.isPhoneDisplayed(phoneNumber), "Номер телефона отображается некорректно");
        assertTrue(paymentPage.isSumDisplayed(sum), "Сумма не отображается");
        assertTrue(paymentPage.isPayButtonSumCorrect(sum), "На кнопке оплаты неверная сумма");
        assertTrue(paymentPage.areCardIconsDisplayed(), "Иконки платёжных систем не отображаются");
    }
}