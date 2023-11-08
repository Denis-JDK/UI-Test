package com.iteco.practic.ui.elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.element;

public class SubscriptionForm {

    public SelenideElement getTitleGit(){
        return element(byAttribute("data-section-id","branching-and-merging")); //берем элемент
    }
    public SelenideElement getTitle(){
        return element(byAttribute("class","rs-h3 rs-h3_theme_light form-section__title")); //берем элемент
    }
    public SelenideElement getTooltip(){ //проверяем появляющуюся надпись об ошибки по вводу email
        return element(byAttribute("data-test", "input input-with-error"));
    }


    public SubscriptionForm insertEmail(String email){
        SelenideElement input = element(byAttribute("class","wt-input__field")).find("input");
        input.setValue(email);
        input.pressEnter();
        return this;
    }

    public SubscriptionForm clickSubscript(){
        //находим элемент кнопка и нажимаем на кнопку
        SelenideElement buttonElement=element(byAttribute("data-test","button"));
        buttonElement.click(); //нажимаем на кнопку с формы с email
        return this;
    }


}
