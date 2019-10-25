package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>();
    private int index = 0;

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
        storage.set(index, resume);
    }

    protected void deleteResume(String uuid) {
        storage.remove(getResume(uuid));
    }

    protected Resume getResume(String uuid) {
        return storage.get(index);
    }

    public int size() {
        return storage.size();
    }

    protected boolean isExist(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                index = i;
                return true;
            }
        }
        return false;
    }
}
