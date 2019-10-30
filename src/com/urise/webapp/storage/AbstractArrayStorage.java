package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10_000;
    Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int size = 0;

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void saveResumeToStorage(Resume resume, int index);

    protected abstract void deleteResumeFromStorage(int index);

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    public List<Resume> getAllSorted() {
        List<Resume> list = Arrays.asList(Arrays.copyOf(storage, size));
        Collections.sort(list);
        return list;
    }

    protected void addResume(Resume resume, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("storage переполнен", resume.getUuid());
        } else {
            saveResumeToStorage(resume, (Integer) index);
            size++;
        }
    }

    protected void updateResume(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    protected void deleteResume(Object index) {
        deleteResumeFromStorage((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    public int size() {
        return size;
    }

    protected Resume getResume(Object index) {
        return storage[(Integer) index];
    }

    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }
}
