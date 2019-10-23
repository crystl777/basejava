package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10_000;
    Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int size = 0;

    protected abstract int getIndex(String uuid);

    protected abstract void saveResumeToStorage(Resume resume, int index);

    protected abstract void deleteResumeFromStorage(int index);

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */


    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected void addResume(Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("storage переполнен", resume.getUuid());
        } else {
            saveResumeToStorage(resume, getIndex(resume.getUuid()));
            size++;
        }
    }

    protected void updateResume(Resume resume) {
        storage[getIndex(resume.getUuid())] = resume;
    }

    protected void deleteResume(String uuid) {
        deleteResumeFromStorage(getIndex(uuid));
        storage[size - 1] = null;
        size--;
    }

    public int size() {
        return size;
    }

    protected Resume getResume(String uuid) {
        return storage[getIndex(uuid)];
    }

    protected boolean isExist(String uuid) {
        return getIndex(uuid) >= 0;
    }
}
