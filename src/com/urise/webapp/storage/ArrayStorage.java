package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;
    private int indexResume = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }


    public void save(Resume resume) {
        if (!duplicateResume(resume.getUuid()) && size < storage.length) {
            storage[size] = resume;
            System.out.println("резюме было добавлено в storage");
            size++;
        } else {
            System.out.println("резюме не было добавлено в storage");
        }
    }

    public void update(Resume resume) {
        if (duplicateResume(resume.getUuid())) {
            storage[indexResume] = resume;
            System.out.println("резюме было заменено");
        } else {
            System.out.println("такого резюме не существует");
        }
    }


    public Resume get(String uuid) {
        if (duplicateResume(uuid)) {
            return storage[indexResume];
        }
        System.out.println("такого резюме не существует");
        return null;
    }


    public void delete(String uuid) {
        if (duplicateResume(uuid)) {
            System.arraycopy(storage, indexResume + 1, storage,
                    indexResume, size - (indexResume + 1));
            storage[size - 1] = null;
            System.out.println("резюме удалено из storage");
            size--;
            return;
        }
        System.out.println("такого резюме не существует");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }


    public boolean duplicateResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                System.out.println("данное резюме " + uuid + " уже существует");
                indexResume = i;
                return true;
            }
        }
        return false;
    }


    public int size() {
        return size;
    }
}
