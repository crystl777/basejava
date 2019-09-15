package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {


    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) != -1) {
            System.out.println("резюме " + resume.getUuid() + " уже существует");
        } else if (size >= storage.length) {
            System.out.println("storage заполнен полностью. резюме не было добавлено");
        } else {
            storage[size] = resume;
            size++;
        }
    }


    public void delete(String uuid) {
        if (getIndex(uuid) == -1) {
            System.out.println("такого резюме не существует");
        } else {
            storage[getIndex(uuid)] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }


    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
