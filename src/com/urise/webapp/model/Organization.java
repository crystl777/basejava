package com.urise.webapp.model;

import java.time.YearMonth;
import java.util.Objects;

public class Organization {

    private String title;
    private String webLink;
    private YearMonth dataStart;
    private YearMonth dataEnd;
    private String text;

    public Organization(String title, String webLink, YearMonth dataStart, YearMonth dataEnd, String text) {
        this.title = title;
        this.webLink = webLink;
        this.dataStart = dataStart;
        this.dataEnd = dataEnd;
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWebLink(String webLink) { this.webLink = webLink; }

    public void setDataStart(YearMonth dataStart) {
        this.dataStart = dataStart;
    }

    public void setDataEnd(YearMonth dataEnd) {
        this.dataEnd = dataEnd;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() { return title; }

    public String getWebLink() { return webLink; }

    public YearMonth getDataStart() { return dataStart; }

    public YearMonth getDataEnd() { return dataEnd; }

    public String getText() { return text; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(webLink, that.webLink) &&
                Objects.equals(dataStart, that.dataStart) &&
                Objects.equals(dataEnd, that.dataEnd) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, webLink, dataStart, dataEnd, text);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "title='" + title + '\'' +
                ", webLink='" + webLink + '\'' +
                ", dataStart=" + dataStart +
                ", dataEnd=" + dataEnd +
                ", text='" + text + '\'' +
                '}';
    }
}
