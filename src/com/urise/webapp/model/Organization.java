package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organization {

    private final Link homePage;
    private List<OrganizationDate> dateList;
    private final String title;
    private final String text;


    public Organization(String name, String url, List<OrganizationDate> dateList, String title, String text) {
        Objects.requireNonNull(dateList, "dateList must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.homePage = new Link(name, url);
        this.dateList = dateList;
        this.title = title;
        this.text = text;
    }

    public Link getHomePage() { return homePage; }

    public List<OrganizationDate> getDateList() { return dateList; }

    public String getTitle() { return title; }

    public String getText() { return text; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage) &&
                dateList.equals(that.dateList) &&
                title.equals(that.title) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, dateList, title, text);
    }

    @Override
    public String toString() {
        StringBuilder date = new StringBuilder("");
        for (int i = 0; i < dateList.size(); i++) {
            date.append(dateList.get(i).toString());
        }
        return "Organization{" +
                "homePage=" + homePage +
                date.toString() +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
