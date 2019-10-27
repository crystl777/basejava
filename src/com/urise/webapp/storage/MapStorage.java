package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> map = new HashMap<>();

    public void clear() {
        map.clear();
    }

    public Resume[] getAll() {
        return map.values().toArray(new Resume[map.size()]);
    }

    protected void addResume(Resume resume, Object searchKey) {
        map.put(resume.getUuid(), resume);
    }

    protected void updateResume(Resume resume, Object searchKey) {
        map.put(resume.getUuid(), resume);
    }

    protected void deleteResume(Object searchKey) {
        map.remove(searchKey.toString());
    }

    protected Resume getResume(Object searchKey) {
        return map.get(searchKey.toString());
    }

    protected boolean isExist(Object searchKey) {
        return map.containsKey(searchKey.toString());
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    public int size() {
        return map.size();
    }
}
