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

    protected int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid()))
                return i;
        }
        return -1;
    }

    protected void addResume(Resume resume, int index) {
        storage.add(resume);

    }

    protected void updateResume(Resume resume, int index) {
        storage.set(index, resume);
    }

    protected void deleteResume(int index) {
        storage.remove(index);
    }

    protected Resume getResume(int index) {
        return storage.get(index);
    }
}
