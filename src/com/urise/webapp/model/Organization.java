package com.urise.webapp.model;

import java.time.YearMonth;
import java.util.Objects;

public class Organization {

    private final String title;
    private final Link homePage;
    private final YearMonth dataStart;
    private final YearMonth dataEnd;
    private final String text;

    public Organization(String title, Link homePage, YearMonth dataStart, YearMonth dataEnd, String text) {
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(dataStart, "dataStart must not be null");
        Objects.requireNonNull(dataEnd, "dataEnd must not be null");
        this.title = title;
        this.homePage = homePage;
        this.dataStart = dataStart;
        this.dataEnd = dataEnd;
        this.text = text;
    }

    public String getTitle() { return title; }

    public Link getHomePage() { return homePage; }

    public YearMonth getDataStart() { return dataStart; }

    public YearMonth getDataEnd() { return dataEnd; }

    public String getText() { return text; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!homePage.equals(that.homePage)) return false;
        if (!dataStart.equals(that.dataStart)) return false;
        if (!dataEnd.equals(that.dataEnd)) return false;
        if (!title.equals(that.title)) return false;
        return Objects.equals(text, that.text);

    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + dataStart.hashCode();
        result = 31 * result + dataEnd.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", dataStart=" + dataStart +
                ", dataEnd=" + dataEnd +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
