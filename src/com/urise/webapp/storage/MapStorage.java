package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage<String> {

    private Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    protected void addResume(Resume resume, String searchKey) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateResume(Resume resume, String searchKey) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(String searchKey) {
        map.remove(searchKey);
    }

    @Override
    protected Resume getResume(String searchKey) {
        return map.get(searchKey);
    }

    @Override
    protected boolean isExist(String searchKey) {
        return map.containsKey(searchKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected List<Resume> getStorageList() {
        return new ArrayList<>(map.values());
    }
}
