package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MtsTest {

    @BeforeAll
    static void preparationOfTest() {
        System.setProperty("chrome-win64.zip", "C:/Users/Nikomi/IdeaProjects/Lesson_15/src/main/resources/");

    }

    @Test
    void PresenceOfAnElement() {// Задание 1 Проверить название блока Онлайн пополнение без комисии
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://www.mts.by//");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement rejectButton = driver.findElement(By.xpath("//button[@class='btn btn_gray cookie__cancel']"));
            wait.until(ExpectedConditions.elementToBeClickable(rejectButton));
            rejectButton.click();

            WebElement block = driver.findElement(By.xpath("//div[@class='pay__wrapper']/h2"));
            String blockText = block.getText();
            if (("Онлайн пополнение" + "\n" + "без комиссии").equals(blockText)) {
                System.out.println("Название блока соответствует ожидаемому.");
            } else {
                System.out.println("Название блока не соответствует ожидаемому. Найдено: " + blockText);
            }
        } finally {
            driver.quit();
        }
    }

    @Test
        // Задание 2 Проверить наличие логотипов платежных систем
    void PresenceOfLogo() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://www.mts.by//");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement rejectButton = driver.findElement(By.xpath("//button[@class='btn btn_gray cookie__cancel']"));
            wait.until(ExpectedConditions.elementToBeClickable(rejectButton));
            rejectButton.click();

            WebElement container = driver.findElement(By.className("pay__partners"));
            List<WebElement> images = container.findElements(By.tagName("img"));

            if (images.size() > 0) {
                System.out.println("Логотипы платежных систем найдены");

                for (WebElement image : images) {
                    String imgSrc = image.getAttribute("src");

                    if (imgSrc != null && !imgSrc.isEmpty()) {
                        System.out.println("Логотип с URL: " + imgSrc);
                    } else {
                        System.out.println("Логотип не найден");
                    }

                    if (image.isDisplayed()) {
                        System.out.println("Логотип отображается на странице.");
                    } else {
                        System.out.println("Логотип не отображается.");
                    }
                }
            } else {
                System.out.println("Логотипы платежных систем не найдены");
            }
        } finally {
            driver.quit();
        }
    }

    @Test
        // Задание 3 Проверить работу ссылки Подробнее о сервисе
    void PresenceOfLink() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://www.mts.by//");
            WebElement rejectButton = driver.findElement(By.xpath("//button[@class='btn btn_gray cookie__cancel']"));
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait2.until(ExpectedConditions.elementToBeClickable(rejectButton));
            rejectButton.click();
            WebElement link = driver.findElement(By.xpath("//a[@href='/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/']"));

            link.click();

            WebElement element = wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                    "//div[@class='container-fluid']")));
            if (element != null) {
                System.out.println("Целевая страница загружена корректно.");
            } else {
                System.out.println("Целевая страница не загружена.");
            }
        } finally {
            driver.quit();
        }
    }

    @Test
        // Задание 4 Проверить заполнение формы и работу кнопки
    void PresenceOfButton() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://www.mts.by//");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement rejectButton = driver.findElement(
                    By.xpath("//button[@class='btn btn_gray cookie__cancel']"));
            wait.until(ExpectedConditions.elementToBeClickable(rejectButton));
            rejectButton.click();

            WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("connection-phone")));
            phoneField.sendKeys("297777777");
            WebElement sumField = driver.findElement(By.id("connection-sum"));
            sumField.sendKeys("4");
            WebElement emailField = driver.findElement(By.id("connection-email"));

            emailField.sendKeys("47jtvfs@gmiil.com");

            WebElement submitButton = driver.findElement(
                    By.xpath("//button[@class='button button__default ']"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", submitButton);

        } finally {
            driver.quit();
        }

    }
}
