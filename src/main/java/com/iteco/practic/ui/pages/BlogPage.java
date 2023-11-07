package com.iteco.practic.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.element;

public class BlogPage {
    public SelenideElement getTheme(String theme){
        return element(byAttribute("data-theme",theme)); //берем элемент по теме оформления на странице
    }

    public void checkCurrentTheme(String theme) {
        getTheme(theme).shouldBe(Condition.exist); //проверяем существует ли элемент с заданной темой оформления сайта, проверка на переключение темы сайта
    }

}
