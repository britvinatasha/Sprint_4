package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SecondOrderPage {

    private final WebDriver driver;

    // Локаторы формы "Про аренду"
    // Локатор для заголовка "Про аренду"
    private final By headingOnSecondOrderPage = By.xpath(".//div[text() = 'Про аренду']");

    // Локатор для поля "Когда привезти самокат"
    private final By rentalStartDayInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    // Локатор для поля "Срок аренды"
    private final By rentalPeriodInput = By.xpath("//div[@class='Dropdown-placeholder']");

    // Локатор для выпающего списка "Срок Арены"
    private final By selectRentalPeriod = By.xpath(".//div[@class='Dropdown-option']");

    // Локатор для поля "Цвет самоката" (не обязательное поле)
    private final By selectColorBlack = By.xpath(".//label[@class='Checkbox_Label__3wxSf']/input[@id='black']");
    private final By selectColorGray = By.xpath(".//label[@class='Checkbox_Label__3wxSf']/input[@id='grey']");

    // Локатор для поля "Комментарий для курьера" (не обязательное поле)
    private final By courierCommentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    // Локатор для кнопки "Заказать"
    private final By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Локатор для формы подтверждения заказа "Хотите оформить заказ?"
    private final By popupFinal = By.xpath(".//div[text() = 'Хотите оформить заказ?']");

    // Локатор для кнопки "Да" в форма подтверждения заказа
    private final By popupFinalButtonYes = By.xpath(".//button[text() = 'Да']");

    // Локатор для заголовка окна "Заказ оформлен"
    private final By createOrderWindow = By.xpath(".//div[text()='Заказ оформлен']");


    public SecondOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilSecondOrderPageIsOpen() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(headingOnSecondOrderPage));
    }

    public void selectRentalStartDayInput(String rentalStartDay) {
        driver.findElement(rentalStartDayInput).sendKeys(rentalStartDay, Keys.ARROW_DOWN, Keys.ENTER);
    }

    public void fillRentalPeriodInput(){
        driver.findElement(rentalPeriodInput).click();
        driver.findElement(selectRentalPeriod).click();
    }

    public void choiceColor(String color) {
        if (!color.isEmpty()) {
            if (color.equals("чёрный жемчуг")) {
                driver.findElement(selectColorBlack).click();  // Используем новый локатор для чёрного цвета
            } else if (color.equals("серая безисходность")) {
                driver.findElement(selectColorGray).click();   // Используем новый локатор для серого цвета
            }
        }
    }
        public void fillCourierCommentInput(String text){
            driver.findElement(courierCommentInput).sendKeys(text);
        }

        public void clickOrderButton() {
            driver.findElement(orderButton).click();
        }

        public void getPopupFinal() {
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.visibilityOfElementLocated(popupFinal));
        }

        public void clickPopupFinalButtonYes() {
            driver.findElement(popupFinalButtonYes).click();
        }

        public void waitUntilCreateOrderWindowIsOpen() {
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.visibilityOfElementLocated(createOrderWindow));
        }

    public void createOrder(String rentalStartDay, String color, String text) {
    waitUntilSecondOrderPageIsOpen();
    selectRentalStartDayInput(rentalStartDay);
    fillRentalPeriodInput();
    choiceColor(color);
    fillCourierCommentInput(text);
    clickOrderButton();
    getPopupFinal();
    clickPopupFinalButtonYes();
    waitUntilCreateOrderWindowIsOpen();
    }
}