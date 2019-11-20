package com.urise.webapp.model.type;

public enum ContactType {
    PHONE_NUMBER("Тел.: "),
    SKYPE("Skype: "),
    E_MAIL("Почта: "),
    LINKEDIN("Профиль LinkedIn: "),
    GITHUB("Профиль GitHub: "),
    STACKOVERFLOW("Профиль StackOverFlow: "),
    HOME_PAGE("Домашняя страница");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
