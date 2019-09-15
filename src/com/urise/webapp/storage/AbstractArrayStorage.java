package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }


    public void update(Resume resume) {
        if (getIndex(resume.getUuid()) < 0) {
            System.out.println("такого резюме не существует");
        } else {
            storage[getIndex(resume.getUuid())] = resume;
            System.out.println("резюме " + resume.getUuid() + " было обновлено");
        }
    }


    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("такого резюме не существует");
            return null;
        }
        return storage[index];
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);
}
