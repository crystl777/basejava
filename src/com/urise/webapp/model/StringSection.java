package com.urise.webapp.model;

import java.util.Objects;

public class StringSection extends AbstractSection {

    private final String text;

    public StringSection(String text) {
        Objects.requireNonNull(text, "content must not be null");
        this.text = text;
    }

    public String getText() {
        return text;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringSection that = (StringSection) o;
        return text.equals(that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "StringSection{" +
                "text='" + text + '\'' +
                '}';
    }
}
