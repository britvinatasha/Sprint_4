package ru.yandex.praktikum.pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;

public class HomePage {
    private final WebDriver driver;

    public static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    // Локатор для кнопки принятия куков
    private final By cookieAcceptButton = By.xpath(".//button[@class='App_CookieButton__3cvqF']");

    // Локатор для кнопки "Заказать" в шапке
    private final By orderButtonInHeader = By.xpath(".//button[@class='Button_Button__ra12g']");

    // Локатор для кнопки "Заказать" в середине страницы
    private final By orderButtonInMiddle = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Локатор для свернутых аккордеонов "Вопросы о важном"
    private final String accordionItemButton = ".//div[@id='accordion__heading-%s']";

    //локатор развернутых аккордеонов в разделе "Вопросы о важном"
    private final String accordionPanelButton = ".//div[@id='accordion__panel-%s']";

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openHomePage() {
        driver.get(PAGE_URL);
    }

    public void clickCookieAcceptButton() {
        driver.findElement(cookieAcceptButton).click();
    }

    public void clickOrderButtonInHeader() {
        driver.findElement(orderButtonInHeader).click();
    }

    public void scrollToAccordionItemButton(String questionNumber) {
        WebElement element = driver.findElement(By.xpath(String.format(accordionItemButton, questionNumber)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickOnAccordionItemButton(String questionNumber) {
        driver.findElement(By.xpath(String.format(accordionItemButton, questionNumber))).click();
    }


    public void clickOrderButtonInMiddle() {
        driver.findElement(orderButtonInMiddle).click();
    }

    public void waitWhenTextInAccordionItemButtonIsVisible(String questionNumber) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(String.format(accordionPanelButton, questionNumber)))));
    }

    public void getTextFromAccordionPanelButtonAndCompare(String questionNumber, String actual) {
        String result = driver.findElement(By.xpath(String.format(accordionPanelButton, questionNumber))).getText();
        assertEquals(actual, result);
    }
}