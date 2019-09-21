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

    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) < 0 && !isFull()) {
            System.out.println("сохраняем новое резюме");
            addResume(resume);
        }
    }

    public void delete(String uuid) {
        if (getIndex(uuid) > 0) {
            deleteResume(storage[getIndex(uuid)].getUuid());
        }
    }

    public void update(Resume resume) {
        if (getIndex(resume.getUuid()) > 0) {
            storage[getIndex(resume.getUuid())] = resume;
            System.out.println("резюме было заменено");
        }
    }

    public Resume get(String uuid) {
        if (getIndex(uuid) > 0) {
            return storage[getIndex(uuid)];
        }
        return null;
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);

    protected boolean isFull() {
        if (size >= storage.length) {
            System.out.println("storage заполнен полностью");
            return true;
        }
        return false;
    }

    protected abstract void addResume(Resume resume);

    protected abstract void deleteResume(String uuid);

    protected void saveResume (Resume resume, int index) {
        storage[index] = resume;
        size++;
    }
}
