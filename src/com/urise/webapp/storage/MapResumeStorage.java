package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {

    private Map<String, Resume> map = new HashMap<>();


    @Override
    protected void addResume(Resume resume, Object searchKey) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        Resume resume = (Resume) searchKey;
        map.remove(resume.getUuid());
    }

    @Override
    protected Resume getResume(Object searchKey) {
        Resume resume = (Resume) searchKey;
        return map.get(resume.getUuid());

    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>(map.values());
        Collections.sort(list);
        return list;
    }

    @Override
    public int size() {
        return map.size();
    }
}
