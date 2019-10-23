package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> map = new HashMap<>();
    private Iterator<Map.Entry<String, Resume>> iter = map.entrySet().iterator();

    public void clear() {
       map.clear();
    }

    public Resume[] getAll() {
        return map.values().toArray(new Resume[map.size()]);
    }

    protected void addResume(Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    protected void updateResume(Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    protected void deleteResume(String uuid) {
        map.remove(uuid);
    }

    protected Resume getResume(String uuid) {
        return map.get(uuid);
    }

    protected boolean isExist(String uuid) {
        return map.containsKey(uuid);
    }

    public int size() {
        return map.size();
    }
}
