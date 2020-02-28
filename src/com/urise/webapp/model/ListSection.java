package com.urise.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {

    private static final long serialVersionUID = -5509188542918453567L;
    private List<String> listComponent;

    public ListSection() {
    }

    public ListSection(List<String> listComponent) {
        Objects.requireNonNull(listComponent, "listComponent must not be null");
        this.listComponent = listComponent;
    }


    public ListSection(String... elements) {
       this(Arrays.asList(elements));
    }


    public List<String> getListComponent() {
        return listComponent;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return listComponent.equals(that.listComponent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listComponent);
    }

    @Override
    public String toString() {
        return listComponent.toString();
    }
}
