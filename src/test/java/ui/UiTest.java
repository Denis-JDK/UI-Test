package ui;

import com.codeborne.selenide.*;
import com.iteco.practic.ui.models.Theme;
import com.iteco.practic.ui.pages.BlogPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.element;


public class UiTest {

    private BlogPage blogPage;
    @BeforeAll //настройка перед всеми тестами, сделать конфигурацию. обязательно метод должен быть статик
    public static void setup(){
        Configuration.browser = "chrome"; //selenide
        Configuration.baseUrl = "https://swiftbook.org/";
    }
    @BeforeEach// настройка перед всеми методами
    public void createPage(){
        blogPage = new BlogPage();

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
        title.scrollIntoView("{block:\"center\"}"); // скролим до этого элемента экран, чтоб при создание скринов теста reports/test скрины были на тестируемом элементе

        title.shouldHave(Condition.exactOwnTextCaseSensitive("Профессии")); //проверяем что именно такой текст есть в элементе и по камелкейсу

    }

    @Test
    public void emailValidationTest(){
        Selenide.open("/users/sign_up/");
        String validEmail="inputEmail"; //важно чтоб валидация была на обьектке input
        //здесь мы достаем input из контейнера div  и только потом проверяем вставку в него значения email
        SelenideElement input = element(byAttribute("class","form-floating email optional user_email")).find("input");
        input.setValue(validEmail);

        input.pressEnter();

        //проверяем появляющуюся надпись об ошибки по вводу email
        SelenideElement toolType = element(byAttribute("class", "invalid-feedback"));
        toolType.shouldBe(Condition.appear); //проверяем еще что сообщение видимо на странице
        toolType.shouldHave(Condition.exactOwnTextCaseSensitive("Email имеет неверное значение"));

        //проверяем что не зарегистрировались с ошибкой в email, путем проверки надписи на странице
        SelenideElement buttonElement=element(byAttribute("data-disable-with","Продолжить"));
        buttonElement.click(); //нажимаем на кнопку с формы с email

        //проверка что не смогли зарегистрироваться, что надпись на странице не изменилась
        SelenideElement title = element(byAttribute("class","unreg__content"));
        title.shouldHave(Condition.exactOwnTextCaseSensitive("Зарегистрируйтесь"));
    }

    @Test // public void defaultExistSidebarCard() аналог PageObject
    public void defaultThemeIsLightPageObjectTest(){
        blogPage.open()
                .checkCurrentTheme(Theme.LIGHT);
    }

    @Test //public void themeSwitcherTest() аналог PageObject
    public void themeSwitcherPageObjectTest(){
        blogPage
                .open()
                .changeTheme()
                .checkCurrentTheme(Theme.DARK);
    }
    @Test //public void subscriptionFormTest(){
    public void subscriptionFormPageObjectTest(){
        Selenide.open("https://git-scm.com/about");

        blogPage
                .getSubscriptionFormTitle()
                .shouldBe(Condition.exactOwnTextCaseSensitive("Branching and Merging")); //проверяем что именно такой текст)
    }




}
