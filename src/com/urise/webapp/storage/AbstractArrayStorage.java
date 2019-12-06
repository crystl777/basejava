package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 10_000;
    Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int size = 0;

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void saveResumeToStorage(Resume resume, int index);

    protected abstract void deleteResumeFromStorage(int index);

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    protected void addResume(Resume resume, Integer index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("storage is full", resume.getUuid());
        } else {
            saveResumeToStorage(resume, (Integer) index);
            size++;
        }
    }

    @Override
    protected void updateResume(Resume resume, Integer index) {
        storage[index] = resume;
    }

    @Override
    protected void deleteResume(Integer index) {
        deleteResumeFromStorage(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume getResume(Integer index) {
        return storage[index];
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }


    @Override
    protected List<Resume> getStorageList() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }
}
