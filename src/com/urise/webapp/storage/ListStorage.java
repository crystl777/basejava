package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }


    public List<Resume> getAllSorted() {
        Collections.sort(storage);
        return storage;
    }

    protected void addResume(Resume resume, Object searchKey) {
        storage.add(resume);
    }

    protected void updateResume(Resume resume, Object searchKey) {
        storage.set((Integer) searchKey, resume);
    }

    protected void deleteResume(Object searchKey) {
        storage.remove(((Integer) searchKey).intValue());
    }

    protected Resume getResume(Object searchKey) {
        return storage.get((Integer) searchKey);
    }

    public int size() {
        return storage.size();
    }

    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }
}
