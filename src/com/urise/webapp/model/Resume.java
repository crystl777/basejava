package com.urise.webapp.model;

import com.urise.webapp.model.type.ContactType;
import com.urise.webapp.model.type.SectionType;

import java.util.HashMap;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    private String fullName;
    private final String uuid;  // Unique identifier
    private HashMap<ContactType, String> contacts = new HashMap<>();
    private HashMap<SectionType, AbstractSection> sections = new HashMap<>();

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }


    public String getFullName() {
        return fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public HashMap<ContactType, String> getContacts() {
        return contacts;
    }

    public HashMap<SectionType, AbstractSection> getSections() {
        return sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        return fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "full name = '" + fullName + '\'' +
                ", uuid = '" + uuid +
                '}';
    }

    @Override
    public int compareTo(Resume resume) {
        int result = getFullName().compareTo(resume.getFullName());
        return result == 0 ? uuid.compareTo(resume.uuid) : result;
    }
}

