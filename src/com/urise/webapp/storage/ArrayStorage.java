package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[3];
    private int size = 0;
    private int indexResume = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }


    public void save(Resume resume) {
        if (size < storage.length) {
            if (!findDuplicateResume(resume.getUuid())) {
                storage[size] = resume;
                System.out.println("резюме было добавлено в storage");
                size++;
            }
        } else {
            System.out.println("storage заполнен полностью. резюме не было добавлено");
        }
    }

    public void update(Resume resume) {
        if (findDuplicateResume(resume.getUuid())) {
            storage[indexResume] = resume;
            System.out.println("резюме было заменено");
        } else {
            System.out.println("такого резюме не существует");
        }
    }


    public Resume get(String uuid) {
        if (findDuplicateResume(uuid)) {
            return storage[indexResume];
        }
        System.out.println("такого резюме не существует");
        return null;
    }


    public void delete(String uuid) {
        if (findDuplicateResume(uuid)) {
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


    public boolean findDuplicateResume(String uuid) {
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
