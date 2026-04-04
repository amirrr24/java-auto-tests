package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PaymentPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//*[@id='pay-section']/div/div/div[2]/section/div/div[1]/div[1]/div[2]/button")
    private WebElement serviceCommunicationTab;

    @FindBy(xpath = "//*[@id='pay-section']/div/div/div[2]/section/div/div[1]/div[2]/button")
    private WebElement homeInternetTab;

    @FindBy(xpath = "//*[@id='pay-section']/div/div/div[2]/section/div/div[1]/div[3]/button")
    private WebElement installmentTab;

    @FindBy(xpath = "//*[@id='pay-section']/div/div/div[2]/section/div/div[1]/div[4]/button")
    private WebElement debtTab;

    @FindBy(xpath = "//*[@id='connection-phone']")
    private WebElement phoneInput;

    @FindBy(xpath = "//*[@id='connection-sum']")
    private WebElement sumInput;

    @FindBy(xpath = "//*[@id='connection-email']")
    private WebElement emailInput;

    @FindBy(xpath = "//*[@id='pay-connection']/button")
    private WebElement continueButton;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public void switchToNewWindow() {
        String originalWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        System.out.println("Переключились на новое окно: " + driver.getCurrentUrl());
    }

    public PaymentPage closeCookieBanner() {
        try {
            Thread.sleep(1000);
            WebElement cookieButton = driver.findElement(By.xpath("//button[contains(text(), 'Принять')]"));
            if (cookieButton.isDisplayed()) {
                cookieButton.click();
                Thread.sleep(500);
            }
        } catch (Exception e) {}
        return this;
    }

    public PaymentPage selectService(String serviceName) {
        Map<String, WebElement> serviceTabs = new HashMap<>();
        serviceTabs.put("Услуги связи", serviceCommunicationTab);
        serviceTabs.put("Домашний интернет", homeInternetTab);
        serviceTabs.put("Рассрочка", installmentTab);
        serviceTabs.put("Задолженность", debtTab);

        WebElement tab = serviceTabs.get(serviceName);
        if (tab != null) {
            tab.click();
            try { Thread.sleep(500); } catch (InterruptedException e) {}
        }
        return this;
    }

    public PaymentPage fillPhone(String phone) {
        phoneInput.clear();
        phoneInput.sendKeys(phone);
        return this;
    }

    public PaymentPage fillSum(String sum) {
        sumInput.clear();
        sumInput.sendKeys(sum);
        return this;
    }

    public PaymentPage fillEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
        return this;
    }

    public PaymentPage clickContinue() {
        continueButton.click();
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
        return this;
    }

    public boolean arePaymentLogosDisplayed() {
        try {
            WebElement logosContainer = driver.findElement(By.xpath("//*[@id='pay-section']/div/div/div[2]/section/div/div[2]/ul"));
            List<WebElement> logos = logosContainer.findElements(By.tagName("img"));
            return logos.size() >= 4;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPhoneDisplayed(String expectedPhone) {
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        try {
            WebElement phoneElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("/html/body/app-root/div/div/div/app-payment-container/section/div/div/div[2]/span")));
            String actualText = phoneElement.getText();
            System.out.println("Текст с номером: '" + actualText + "'");
            return actualText.contains("375" + expectedPhone);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            return false;
        }
    }

    public boolean isSumDisplayed(String expectedSum) {
        try {
            WebElement sumElement = driver.findElement(By.xpath("/html/body/app-root/div/div/div/app-payment-container/section/div/div/div[1]/div[1]/span"));
            return sumElement.getText().contains(expectedSum);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPayButtonSumCorrect(String expectedSum) {
        try {
            WebElement buttonElement = driver.findElement(By.xpath("/html/body/app-root/div/div/div/app-payment-container/section/div/app-card-page/div/div[1]"));
            return buttonElement.getText().contains(expectedSum);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areCardIconsDisplayed() {
        try {
            List<WebElement> icons = driver.findElements(By.xpath("//img[contains(@src, 'visa') or contains(@src, 'mastercard')]"));
            return icons.size() >= 2;
        } catch (Exception e) {
            return false;
        }
    }
}