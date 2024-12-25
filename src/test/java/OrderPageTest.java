import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.pageobject.FirstOrderPage;
import ru.yandex.praktikum.pageobject.HomePage;
import ru.yandex.praktikum.pageobject.SecondOrderPage;

import static ru.yandex.praktikum.pageobject.HomePage.PAGE_URL;

@RunWith(Parameterized.class)
public class OrderPageTest extends BaseTest {

    private final int buttonOnPage;
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String rentalStartDay;
    private final String color;
    private final String text;

    public OrderPageTest(int buttonOnPage, String name,String surname, String address, String metroStation, String phoneNumber, String rentalStartDay, String color, String text){
        this.buttonOnPage = buttonOnPage;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.rentalStartDay = rentalStartDay;
        this.color = color;
        this.text = text;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {1,"Саша", "Шурочкин", "ул. Планерная. 1", "Садовая", "88008008080", "28.12.2024", "чёрный жемчуг", "без комментариев"},
                {2,"Дед", "Мороз", "ул. Холодная", "Сокольники", "1233567899", "28.12.2024", "чёрный жемчуг", "хо-хо-хо"},
                {1,"Человек", "Разумный", "Красная площадь", "ВДНХ", "+79001001010", "28.12.2024", "серая безысходность", "можно без самоката"},
                {2, "Михайло", "Пупин", "Трг Републике 1", "Чистые пруды", "+79313113131", "28.12.2024", "серая безысходность", "Живели!"}
        };
    }

    @Before
    public void setUp () {
        super.setUp("chrome");
        driver.get(PAGE_URL);
    }

    @Test
    public void testCheckOrderCreate() {
        HomePage objHomePage = new HomePage(driver);
        objHomePage.openHomePage();
        objHomePage.clickCookieAcceptButton();

        switch (buttonOnPage) {
            case 1:
                objHomePage.clickOrderButtonInHeader();
                break;
            case 2:
                objHomePage.clickOrderButtonInMiddle();
                break;
        }

        FirstOrderPage objFirstOrderPage = new FirstOrderPage(driver);
        objFirstOrderPage.waitUntilFirstOrderPageIsOpen();
        objFirstOrderPage.goToSecondOrderPage(name, surname, address, metroStation, phoneNumber);

        SecondOrderPage objSecondOrderPage = new SecondOrderPage(driver);
        objSecondOrderPage.waitUntilSecondOrderPageIsOpen();
        objSecondOrderPage.createOrder(rentalStartDay, color, text);

    }

    @After
    public void closeBrowser() {
        super.closeBrowser();
    }

}