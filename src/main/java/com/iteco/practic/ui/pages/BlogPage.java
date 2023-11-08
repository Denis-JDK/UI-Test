package com.iteco.practic.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.iteco.practic.ui.elements.SubscriptionForm;
import com.iteco.practic.ui.models.Theme;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.element;

public class BlogPage extends Page {

    private final SubscriptionForm subscriptionForm;

    public BlogPage() {
        subscriptionForm = new SubscriptionForm();

    }

    public SubscriptionForm getSubscriptionForm() {
        return subscriptionForm;
    }

    public BlogPage open(){
        Selenide.open("/blog");
        return this;
    }


}
