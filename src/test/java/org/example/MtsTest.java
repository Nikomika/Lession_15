package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MtsTest {

    @BeforeAll
    static void preparationOfTest() {
        System.setProperty("chromedriver-win64.zip", "C:/Users/Nikomi/IdeaProjects/Lesson_15/src/main/resources/");
    }

    @Test
    void PresenceOfAnElement() {// Задание 1 Проверить название блока Онлайн пополнение без комисии
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        try {
            LoginPage mtsPage = new LoginPage(driver);
            mtsPage.openPage("https://www.mts.by/");
            mtsPage.clickRejectButton("//button[@class='btn btn_gray cookie__cancel']");
            mtsPage.checkingProposal("Онлайн пополнение" + "\n" + "без комиссии", "//div[@class='pay__wrapper']/h2");
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
            LoginPage mtsPage = new LoginPage(driver);
            mtsPage.openPage("https://www.mts.by/");
            mtsPage.clickRejectButton("//button[@class='btn btn_gray cookie__cancel']");
            mtsPage.checkingListLogo();
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
            LoginPage mtsPage = new LoginPage(driver);
            mtsPage.openPage("https://www.mts.by/");
            mtsPage.clickRejectButton("//button[@class='btn btn_gray cookie__cancel']");
            mtsPage.clickLinkPage("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/");
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
            LoginPage mtsPage = new LoginPage(driver);
            mtsPage.openPage("https://www.mts.by/");
            mtsPage.clickRejectButton("//button[@class='btn btn_gray cookie__cancel']");
            mtsPage.fillingInFieldAndWrite("connection-phone", "297777777");
            mtsPage.fillingInFieldAndWrite("connection-sum", "6");
            mtsPage.fillingInFieldAndWrite("connection-email", "77jthjs@gmiil.com");
            mtsPage.clickRejectButton("//button[@class='button button__default ']");

        } finally {
            driver.quit();
        }

    }

    @Test
        // Лабораторная 16 Задание 1.1 Проверить плесходеры Услуги Связи
    void PresenceOfText() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        try {
            LoginPage mtsPage = new LoginPage(driver);
            mtsPage.openPage("https://www.mts.by/");
            mtsPage.clickRejectButton("//button[@class='btn btn_gray cookie__cancel']");
            assertEquals("Номер телефона",
                    mtsPage.fillingInFieldAndCheck("connection-phone"));
            assertEquals("Сумма",
                    mtsPage.fillingInFieldAndCheck("connection-sum"));
            assertEquals("E-mail для отправки чека",
                    mtsPage.fillingInFieldAndCheck("connection-email"));
        } finally {
            driver.quit();
        }

    }

    @Test
        // Лабораторная 16 Задание 1.1 Проверить плесходеры Домашний интернет
    void PresenceOfTextInt() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        try {
            LoginPage mtsPage = new LoginPage(driver);
            mtsPage.openPage("https://www.mts.by/");
            mtsPage.clickRejectButton("//button[@class='btn btn_gray cookie__cancel']");
            mtsPage.selectFromButton("//button[@class='select__header']", "Домашний интернет");
            assertEquals("Номер абонента",
                    mtsPage.fillingInFieldAndCheck("internet-phone"));
            assertEquals("Сумма",
                    mtsPage.fillingInFieldAndCheck("internet-sum"));
            assertEquals("E-mail для отправки чека",
                    mtsPage.fillingInFieldAndCheck("internet-email"));
        } finally {
            driver.quit();
        }

    }

    @Test
        // Лабораторная 16 Задание 1.1 Проверить плесходеры Рассрочка
    void PresenceOfTextInstalment() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        try {
            LoginPage mtsPage = new LoginPage(driver);
            mtsPage.openPage("https://www.mts.by/");
            mtsPage.clickRejectButton("//button[@class='btn btn_gray cookie__cancel']");
            mtsPage.selectFromButton("//button[@class='select__header']", "Рассрочка");
            assertEquals("Номер счета на 44",
                    mtsPage.fillingInFieldAndCheck("score-instalment"));
            assertEquals("Сумма",
                    mtsPage.fillingInFieldAndCheck("instalment-sum"));
            assertEquals("E-mail для отправки чека",
                    mtsPage.fillingInFieldAndCheck("instalment-email"));
        } finally {
            driver.quit();
        }

    }

    @Test
        // Лабораторная 16 Задание 1.1 Проверить плесходеры Задолженность
    void PresenceOfTextArrears() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        try {
            LoginPage mtsPage = new LoginPage(driver);
            mtsPage.openPage("https://www.mts.by/");
            mtsPage.clickRejectButton("//button[@class='btn btn_gray cookie__cancel']");
            mtsPage.selectFromButton("//button[@class='select__header']", "Задолженность");
            assertEquals("Номер счета на 2073",
                    mtsPage.fillingInFieldAndCheck("score-arrears"));
            assertEquals("Сумма",
                    mtsPage.fillingInFieldAndCheck("arrears-sum"));
            assertEquals("E-mail для отправки чека",
                    mtsPage.fillingInFieldAndCheck("arrears-email"));
        } finally {
            driver.quit();
        }

    }


    @Test
    void PresenceOfFrame() { //Лабораторная 16 Задание 1.2 Проверить плесходеры в отдельном окне
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        try {
            LoginPage mtsPage = new LoginPage(driver);
            mtsPage.openPage("https://www.mts.by/");
            mtsPage.clickRejectButton("//button[@class='btn btn_gray cookie__cancel']");

            mtsPage.fillingInFieldAndWrite("connection-phone", "297777777");
            mtsPage.fillingInFieldAndWrite("connection-sum", "5");
            mtsPage.fillingInFieldAndWrite("connection-email", "47jtvfs@gmiil.com");
            mtsPage.clickRejectButton("//button[@class='button button__default ']");
            mtsPage.fillingInFieldFrame(); //Переключение на фрейм

            assertEquals("Оплата: Услуги связи Номер:375297777777",
                    driver.findElement(
                            By.xpath("//div[@class='pay-description__text']//span[text()]")).getText());

            assertEquals("5.00 BYN",driver.findElement(By.xpath("//span[text()]")).getText());

            driver.findElement(
                    By.xpath("//img[@src='assets/images/payment-icons/card-types/visa-system.svg']")).isDisplayed();
            driver.findElement(
                    By.xpath("//img[@src='assets/images/payment-icons/card-types/mastercard-system.svg']")).isDisplayed();
            driver.findElement(
                    By.xpath("//img[@src='assets/images/payment-icons/card-types/belkart-system.svg']")).isDisplayed();
            driver.findElement(
                    By.xpath("//div[@class=\"cards-brands cards-brands_random ng-tns-c61-0 ng-star-inserted\"]")).isDisplayed();
            driver.findElement(
                    By.xpath("//div[@class=\"cards-brands cards-brands_random ng-tns-c61-0 ng-star-inserted\"]")).isDisplayed();

            assertEquals("Срок действия", driver.findElement(
                            By.xpath("//label[@class='ng-tns-c46-4 ng-star-inserted']")).getText());
            assertEquals( "CVC", driver.findElement(
                    By.xpath("//label[@class='ng-tns-c46-5 ng-star-inserted']")).getText());
            assertEquals("Имя держателя (как на карте)", driver.findElement(
                    By.xpath("//label[@class='ng-tns-c46-3 ng-star-inserted']")).getText());
            assertEquals("Оплатить 5.00 BYN",
                    driver.findElement(
                            By.xpath("//button[text()]")).getText());
        } finally {
            driver.quit();
        }

    }
}
