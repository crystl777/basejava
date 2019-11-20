package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class ListInfoSection extends AbstractSection {

    private List<InfoSection> infoComponent = new ArrayList<>();

    public List<InfoSection> getInfoComponent() {
        return infoComponent;
    }

    @Override
    public String toString() {
        StringBuilder infoComponentText = new StringBuilder("ListInfoSection \"infoComponent= \"");
        for (InfoSection s : infoComponent) {
            infoComponentText.append(" ").append(s).append(" ");
        }
        return infoComponentText.toString();
    }
}
