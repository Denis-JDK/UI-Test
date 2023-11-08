package com.iteco.practic.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.iteco.practic.ui.models.Theme;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.element;

public class Page {
    public SelenideElement getTheme(Theme theme){ //getter поэтому возвращаем не BlogPage
        return element(byAttribute("data-theme",theme.getAttributeValue())); //берем элемент по теме оформления на странице
    }


    public void checkCurrentTheme(Theme theme) { // assertion проверка поэтому возвращаем не BlogPage
        getTheme(theme).shouldBe(Condition.exist); //проверяем существует ли элемент с заданной темой оформления сайта, проверка на переключение темы сайта
    }

    public Page changeTheme(){ //находим элемент-рубильник переключения темы на странице сайта, и переключаем(кликаем по нему)
        SelenideElement themeSwitcher = element(byAttribute("data-test","theme-switcher"));
        themeSwitcher.click();
        return this;
    }

}
