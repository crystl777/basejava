package com.urise.webapp.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    private String fullName;
    private final String uuid;  // Unique identifier

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
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

