package com.iteco.practic.ui.models;

public enum Theme {
    LIGHT("light"),
    DARK("dark");

    private final String attributeValue;

    Theme(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public String getAttributeValue() {
        return attributeValue;
    }
}
