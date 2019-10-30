package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> map = new HashMap<>();


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

    public void clear() {
        map.clear();
    }

    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>(map.values());
        Collections.sort(list);
        return list;
    }

    public int size() {
        return map.size();
    }
}
