package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends AbstractSection {

    private List<String> listComponent = new ArrayList<>();

    public List<String> getListComponent() {
        return listComponent;
    }

    @Override
    public String toString() {
        StringBuilder listComponentText = new StringBuilder("ListSection \"listComponent= \"");
        for (String s : listComponent) {
            listComponentText.append(" ").append(s).append(" ");
        }
        return listComponentText.toString();
    }
}
