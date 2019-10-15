package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    private int index = 0;

    protected int size = 0;

    protected abstract int getIndex(String uuid);

    protected abstract void addResume(Resume resume, int index);

    protected abstract void updateResume(Resume resume, int index);

    protected abstract void deleteResume(int index);

    protected abstract Resume getResume(int index);

    public void save(Resume resume) {
        index = getIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            addResume(resume, index);
            size++;
        }
    }

    public void update(Resume resume) {
        index = getIndex(resume.getUuid());
        if (index >= 0) {
            updateResume(resume, index);
            System.out.println("резюме было заменено");
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public void delete(String uuid) {
        index = getIndex(uuid);
        if (index >= 0) {
            deleteResume(index);
            size--;
            System.out.println("резюме " + uuid + " было удалено");
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public Resume get(String uuid) {
        index = getIndex(uuid);
        if (index >= 0) {
            return getResume(index);
        }
        throw new NotExistStorageException(uuid);
    }

    public int size() {
        return size;
    }
}
