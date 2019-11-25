package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {

    private final List<String> listComponent;

    public ListSection(List<String> listComponent) {
        Objects.requireNonNull(listComponent, "items must not be null");
        this.listComponent = listComponent;
    }

    public List<String> getListComponent() {
        return listComponent;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(listComponent, that.listComponent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listComponent);
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
