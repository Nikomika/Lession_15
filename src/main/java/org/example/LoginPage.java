package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By submitButtonLoc = By.xpath("//button[@class='button button__default ']");
    private final By linkLoc = By.xpath("//a[@href='/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/']");
    private final By checkLogoLoc = By.className("pay__partners");

    // Конструктор
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void openPage(String url) {
        driver.get(url);
    }

    public void clickRejectButton(String rejectButtonLoc) {
        WebElement rejectButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(rejectButtonLoc)));
        rejectButton.click();
    }

    public void clickSubmitButton() {
        WebElement submitButton = driver.findElement(submitButtonLoc);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", submitButton);
    }

    public void clickLinkPage(String linkButtonLoc) {
        WebElement linkPage = driver.findElement(linkLoc);
        linkPage.click();
        wait.until(ExpectedConditions.urlContains(linkButtonLoc));
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains(linkButtonLoc)) {
            System.out.println("Целевая страница загружена корректно.");
        } else {
            System.out.println("Целевая страница не загружена.");
        }
    }

    public void checkingProposal(String chekString, String h2TextLoc) {
        By h2TextLoc2 = By.xpath(h2TextLoc);
        WebElement block = driver.findElement(h2TextLoc2);
        String blockText = block.getText();
        if (chekString.equals(blockText)) {
            System.out.println("Название блока соответствует ожидаемому.");
        } else {
            System.out.println("Название блока не соответствует ожидаемому. Найдено: " + blockText);
        }
    }

    public void checkingListLogo() {
        WebElement container = driver.findElement(checkLogoLoc);
        List<WebElement> images = container.findElements(By.tagName("img"));

        if (images.size() > 0) {
            System.out.println("Логотипы платежных систем найдены");

            for (WebElement image : images) {
                String imgSrc = image.getAttribute("alt");

                if (imgSrc != null && !imgSrc.isEmpty()) {
                    System.out.println("Логотип: " + imgSrc);
                } else {
                    System.out.println("Логотип не найден");
                }

                if (image.isDisplayed()) {
                    System.out.println(imgSrc + " отображается на странице.");
                } else {
                    System.out.println("Логотип не отображается.");
                }
            }
        } else {
            System.out.println("Логотипы платежных систем не найдены");
        }
    }
    public void fillingInFieldAndWrite(String id, String keys) {
        WebElement field = driver.findElement(By.id(id));
        field.sendKeys(keys);
    }

    public String fillingInFieldAndCheck (String id){
        String placeholderText;
        WebElement field = driver.findElement(By.id(id));
        return placeholderText = field.getAttribute("placeholder");
    }

    public void selectFromButton (String xpath, String model){

        WebElement dropdownElement = driver.findElement(By.xpath(xpath));
        dropdownElement.click();
        List<WebElement> listItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//li[@class='select__item']")));
        String targetItemText = model;
        for (WebElement item : listItems) {
            if (item.getText().equals(targetItemText)) {
                item.click();
                break;
            }
        }
    }

    public void fillingInFieldFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                By.xpath("//iframe[@class='bepaid-iframe']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("section.payment-page.payment-page_mobile.payment-page_pays")));
    }

}