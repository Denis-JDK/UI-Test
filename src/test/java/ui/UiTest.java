package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UiTest {
    @BeforeAll //настройка перед всеми тестами, сделать конфигурацию. обязательно метод должен быть статик
    public static void setup(){
        Configuration.browser = "chrome"; //selenide
    }
    @Test
    public void uiTest(){
        Selenide.open("http://www.semrush.com/blog/");
    }
}
