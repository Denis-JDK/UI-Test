package com.iteco.practic.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.element;

public class BlogPage {
    public BlogPage open(){
        Selenide.open("/blog");
        return this;
    }

    public SelenideElement getTheme(String theme){ //getter поэтому возвращаем не BlogPage
        return element(byAttribute("data-theme",theme)); //берем элемент по теме оформления на странице
    }

    public void checkCurrentTheme(String theme) { // assertion проверка поэтому возвращаем не BlogPage
        getTheme(theme).shouldBe(Condition.exist); //проверяем существует ли элемент с заданной темой оформления сайта, проверка на переключение темы сайта
    }

    public BlogPage changeTheme(){ //находим элемент-рубильник переключения темы на странице сайта, и переключаем(кликаем по нему)
        SelenideElement themeSwitcher = element(byAttribute("data-test","theme-switcher"));
        themeSwitcher.click();
        return this;
    }

}
