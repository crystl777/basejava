package com.urise.webapp.model;

import java.time.YearMonth;

public class InfoSection {

    private String title;
    private YearMonth dataStart;
    private YearMonth dataEnd;
    private String text;

    public InfoSection(String title, YearMonth dataStart, YearMonth dataEnd, String text) {
        this.title = title;
        this.dataStart = dataStart;
        this.dataEnd = dataEnd;
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDataStart(YearMonth dataStart) {
        this.dataStart = dataStart;
    }

    public void setDataEnd(YearMonth dataEnd) {
        this.dataEnd = dataEnd;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public YearMonth getDataStart() {
        return dataStart;
    }

    public YearMonth getDataEnd() {
        return dataEnd;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "InfoSection{" +
                "title='" + title + '\'' +
                ", dataStart=" + dataStart +
                ", dataEnd=" + dataEnd +
                ", text='" + text + '\'' +
                '}';
    }
}
