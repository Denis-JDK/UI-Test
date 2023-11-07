package ui;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.element;


public class UiTest {
    @BeforeAll //настройка перед всеми тестами, сделать конфигурацию. обязательно метод должен быть статик
    public static void setup(){
        Configuration.browser = "chrome"; //selenide
        Configuration.baseUrl = "https://swiftbook.org/";
    }
    @Test
    public void webElementSelectorsTest(){
        Selenide.open("/shop");
        SelenideElement element= element(By.xpath("//*[@id=\"shops\"]/script[8]")); //плохой способ выберать элемент тк если измениться тэг то тест сломается
        SelenideElement element1 = element("<h1 class=\"nav__info\">\n" +//плохой способ выберать элемент тк если измениться тэг то тест сломается
                "Курсы и професии по мобильной разработке\n" +
                "</h1>");
        SelenideElement elementByDataAttribute = element(byAttribute("class","nav__info")); //правильный способ, не зависит от тэгов по пути к элементу для тестирования
        System.out.println();
    }
    @Test
    public void defaultExistSidebarCard(){
        Selenide.open("/shop");
        SelenideElement element = element(byAttribute("class","sidebar__card"));
        // SelenideElement element= Selenide.element(By.xpath("/html/body/div[1]/div[1]/div/div[2]/a[1]"));
        element.shouldBe(Condition.exist);
    }

    @Test
    public void themeSwitcherTest(){ //переключаем и проверяем существует ли уже новый элемент по атрибуту, так как атрибут элемента меняется после клик по нему
        Selenide.open("/shop");
        SelenideElement element = element(byAttribute("class","sidebar"));
        element.click();

        SelenideElement currentButton = element(byAttribute("class","sidebar active"));
        currentButton.shouldBe(Condition.exist);

    }

    @Test
    public void subscriptionFormTest(){
        Selenide.open("/shop");
        SelenideElement title = element(byAttribute("class","row"));
        title.scrollIntoView(true); // скролим до этого элемента экран, чтоб при создание скринов теста reports/test скрины были на тестируемом элементе

        title.shouldHave(Condition.exactOwnTextCaseSensitive("Профессии")); //проверяем что именно такой текст есть в элементе и по камелкейсу
    }


}
