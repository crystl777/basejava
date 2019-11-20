package com.urise.webapp.model;

public class StringSection extends AbstractSection {

    private String text;

    public StringSection(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "StringSection{" +
                "text='" + text + '\'' +
                '}';
    }
}
