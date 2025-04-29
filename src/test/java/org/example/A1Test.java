package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A1Test {

    @BeforeAll
    static void preparationOfTest() {
        System.setProperty("chromedriver-win64.zip", "src/main/resources/chromedriver.exe");

    }

    @Test
    void PresenceOfAnElement() {// Задание 1 Проверить название блока Онлайн пополнение без комиссии
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://www.a1.by/ru//");
        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.className("cookies-buttons-wrap")
            ));

            WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[.//span[text()='Принять']]")
            ));
            acceptButton.click();
            WebElement emailField = driver.findElement(By.id("i-subscribe-input"));
            assertEquals("Введите свой email", emailField.getAttribute("placeholder"));

        } catch (Exception e) {
            System.out.println("Ошибка при попытке нажать кнопку: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }


}
