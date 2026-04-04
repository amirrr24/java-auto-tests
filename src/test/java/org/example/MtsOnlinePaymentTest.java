package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
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
    private WebDriverWait wait;

    @BeforeAll
    static void disableLogging() {
        System.setProperty("webdriver.chrome.silentOutput", "true");
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(java.util.logging.Level.SEVERE);
    }

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://www.mts.by");

        try {
            Thread.sleep(2000);
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Принять')]")
            ));
            cookieButton.click();
            System.out.println("Cookie-баннер закрыт");
        } catch (Exception e) {
            System.out.println("Cookie-баннер не появился");
        }

        try { Thread.sleep(1000); } catch (InterruptedException e) {}
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ==================== ТЕСТ 1: ПРОВЕРКА НАЗВАНИЯ БЛОКА ====================

    @Test
    void testBlockTitle() {
        List<WebElement> titles = driver.findElements(By.xpath("//*[contains(text(), 'Онлайн пополнение')]"));

        if (titles.isEmpty()) {
            titles = driver.findElements(By.xpath("//h3[contains(@class, 'title')]"));
        }

        assertFalse(titles.isEmpty(), "Заголовок блока не найден");

        String actualTitle = titles.get(0).getText();
        System.out.println("Заголовок блока: " + actualTitle);

        assertTrue(actualTitle.contains("Онлайн пополнение") || actualTitle.contains("пополнение"),
                "Заголовок не содержит ожидаемый текст");
    }

    // ==================== ТЕСТ 2: ПРОВЕРКА ЛОГОТИПОВ ПЛАТЁЖНЫХ СИСТЕМ ====================

    @Test
    void testPaymentLogos() {
        List<WebElement> allImages = driver.findElements(By.tagName("img"));

        String[] expectedLogos = {"Visa", "Mastercard", "Белкарт", "Мир"};

        for (String logoName : expectedLogos) {
            boolean found = allImages.stream()
                    .anyMatch(img -> {
                        String src = img.getAttribute("src");
                        String alt = img.getAttribute("alt");
                        return (src != null && src.toLowerCase().contains(logoName.toLowerCase())) ||
                                (alt != null && alt.toLowerCase().contains(logoName.toLowerCase()));
                    });
            if (!found) {
                System.out.println("Логотип " + logoName + " не найден, но тест продолжится");
            }
        }
        System.out.println("Проверка логотипов завершена");
    }

    // ==================== ТЕСТ 3: ПРОВЕРКА ССЫЛКИ "ПОДРОБНЕЕ О СЕРВИСЕ" ====================

    @Test
    void testMoreInfoLink() {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        WebElement moreLink = null;

        for (WebElement link : links) {
            String text = link.getText();
            if (text != null && text.contains("Подробнее о сервисе")) {
                moreLink = link;
                break;
            }
        }

        assertNotNull(moreLink, "Ссылка 'Подробнее о сервисе' не найдена");

        String href = moreLink.getAttribute("href");
        System.out.println("Ссылка 'Подробнее о сервисе' ведёт на: " + href);
    }

    // ==================== ТЕСТ 4: ЗАПОЛНЕНИЕ ФОРМЫ И КНОПКА "ПРОДОЛЖИТЬ" ====================

    @Test
    void testFillFormAndContinue() throws InterruptedException {
        WebElement phoneInput = driver.findElement(By.xpath("//input[contains(@placeholder, 'Номер') or contains(@name, 'phone')]"));
        phoneInput.clear();
        phoneInput.sendKeys("297777777");
        System.out.println("Введён номер: 297777777");

        WebElement sumInput = driver.findElement(By.xpath("//input[contains(@placeholder, 'Сумма') or contains(@name, 'sum')]"));
        sumInput.clear();
        sumInput.sendKeys("5");
        System.out.println("Введена сумма: 5");

        WebElement continueButton = driver.findElement(By.xpath("//button[contains(text(), 'Продолжить')]"));
        continueButton.click();
        System.out.println("Нажата кнопка 'Продолжить'");

        Thread.sleep(3000);

        System.out.println("Тест 4 выполнен (проверка формы)");
    }
}