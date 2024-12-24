package ru.yandex.praktikum.pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FirstOrderPage {
    private final WebDriver driver;

    // Локаторы формы "Для кого самокат"
    //Локатор для заголовка страницы "Для кого самокат"
    private final By headingOnFirstOrderPage = By.xpath(".//div[text()='Для кого самокат']");

    //Локатор поля "Имя"
    private final By nameInput = By.xpath(".//input[@placeholder='* Имя']");

    //Локатор поля "Фамилия"
    private final By surnameInput = By.xpath(".//input[@placeholder='* Фамилия']");

    //Локатор поля "Адрес"
    private final By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    //Локатор поля "Станция метро"
    private final By metroStationInput = By.xpath(".//input[@placeholder='* Станция метро']");

    //Локатор для выпадающего списка поля "Станция метро"
    //private By metroStationList = By.xpath(".//li[@class='select-search__row']");

    //Локатор поля "Телефон"
    private final By phoneNumberInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //Локатор для кнопки "Далее"
    private final By nextButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public FirstOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilFirstOrderPageIsOpen() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(headingOnFirstOrderPage));
    }
    public void fillNameInput(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    public void fillSurnameInput(String surname) {
        driver.findElement(surnameInput).sendKeys(surname);
    }

    public void fillAddressInput(String address) {
        driver.findElement(addressInput).sendKeys(address);
    }

    public void selectMetroStationInput(String metroStation) {
        driver.findElement(metroStationInput).sendKeys(metroStation, Keys.ARROW_DOWN, Keys.ENTER);
    }

    public void fillPhoneNumberInput(String phoneNumber) {
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void goToSecondOrderPage(String name, String surname, String address, String metroStation, String phoneNumber) {
        waitUntilFirstOrderPageIsOpen();
        fillNameInput(name);
        fillSurnameInput(surname);
        fillAddressInput(address);
        selectMetroStationInput(metroStation);
        fillPhoneNumberInput(phoneNumber);
        clickNextButton();
    }
}

