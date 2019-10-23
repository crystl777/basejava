package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    protected void addResume(Resume resume) {
        storage.add(resume);
    }

    protected void updateResume(Resume resume) {
        int index = storage.indexOf(resume);
        storage.set(index, resume);
    }

    protected void deleteResume(String uuid) {
        storage.remove(getResume(uuid));
    }

    protected Resume getResume(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid()))
                return storage.get(i);
        }
        return storage.get(-1);
    }

    public int size() {
        return storage.size();
    }

    protected boolean isExist(String uuid) {
        for (Resume resume : storage) {
            if (uuid.equals(resume.getUuid())) {
                return true;
            }
        }
        return false;
    }
}
