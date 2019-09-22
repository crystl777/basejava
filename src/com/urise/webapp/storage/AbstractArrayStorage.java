package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;
    private int index = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (!isFull()) {
            index = getIndex(resume.getUuid());
            if (index < 0) {
                addResume(resume, index);
                size++;
                System.out.println("резюме было добавлено в storage");
            } else {
                System.out.println("резме " + resume.getUuid() + " уже существует");
            }
        }
    }

    public void delete(String uuid) {
        index = getIndex(uuid);
        if (index >= 0) {
            deleteResume(index);
            storage[size - 1] = null;
            size--;
            System.out.println("резюме " + uuid + " было удалено");
        } else {
            System.out.println("резюме " + uuid + " не было найдено");
        }
    }

    public void update(Resume resume) {
        index = getIndex(resume.getUuid());
        if (index > 0) {
            storage[index] = resume;
            System.out.println("резюме было заменено");
        }
    }

    public Resume get(String uuid) {
        index = getIndex(uuid);
        if (index > 0) {
            return storage[index];
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

    private boolean isFull() {
        if (size >= storage.length) {
            System.out.println("storage заполнен полностью");
            return true;
        }
        return false;
    }

    protected abstract void addResume(Resume resume, int index);

    protected abstract void deleteResume(int index);
}
