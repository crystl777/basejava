package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract void addResume(Resume resume);

    protected abstract void updateResume(Resume resume);

    protected abstract void deleteResume(String uuid);

    protected abstract Resume getResume(String uuid);

    protected abstract boolean isExist(String uuid);


    public void save(Resume resume) {
        if (isExist(resume.getUuid())) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            addResume(resume);
            System.out.println("резюме было добавлено");
        }
    }

    public void update(Resume resume) {
        if (isExist(resume.getUuid())) {
            updateResume(resume);
            System.out.println("резюме было заменено");
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public void delete(String uuid) {
        if (isExist(uuid)) {
            deleteResume(uuid);
            System.out.println("резюме " + uuid + " было удалено");
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public Resume get(String uuid) {
        if (isExist(uuid)) {
            return getResume(uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }
}
