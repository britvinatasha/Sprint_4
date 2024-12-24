import ru.yandex.praktikum.pageobject.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static ru.yandex.praktikum.pageobject.HomePage.PAGE_URL;


@RunWith(Parameterized.class)
 public class FAQTest {

     private static WebDriver driver;

     private final int questionNumber;
     private final String answerText;

     public FAQTest(int questionNumber, String answerText) {
         this.questionNumber = questionNumber;
         this.answerText = answerText;
     }

     @Parameterized.Parameters
     public static Object[][] getAccordionText() {
         return new Object[][]{
                 {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой." },
                 {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим." },
                 {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                 {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее." },
                 {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                 {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                 {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                 {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
         };
     }
         @Before
         public void setUp () {
             WebDriverManager.chromedriver().setup();
             driver = new ChromeDriver();
             //WebDriverManager.firefoxdriver().setup();
             //driver = new FirefoxDriver();
             driver.get(PAGE_URL);
         }

         @Test

         public void checkQuestionsText () {
             HomePage objHomePage = new HomePage(driver);
             objHomePage.openHomePage();
             objHomePage.clickCookieAcceptButton();
             objHomePage.scrollToAccordionItemButton(String.valueOf(questionNumber));
             objHomePage.clickOnAccordionItemButton(String.valueOf(questionNumber));
             objHomePage.waitWhenTextInAccordionItemButtonIsVisible(String.valueOf(questionNumber));
             objHomePage.getTextFromAccordionPanelButtonAndCompare(String.valueOf(questionNumber), answerText);
         }

    @After
    public void closeBrowser () {
        driver.quit();
    }
}




