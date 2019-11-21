package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection {

    private List<Organization> organizationList = new ArrayList<>();

    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return Objects.equals(organizationList, that.organizationList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizationList);
    }

    @Override
    public String toString() {
        StringBuilder organizationsListText = new StringBuilder("OrganizationSection \"organizationsList= \"");
        for (Organization s : organizationList) {
            organizationsListText.append(" ").append(s).append(" ");
        }
        return organizationsListText.toString();
    }
}
