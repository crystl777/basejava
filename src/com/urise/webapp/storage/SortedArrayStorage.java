package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {


    @Override
    public void save(Resume resume) {
        if (size == 0) {
            storage[0] = resume;
            size++;
        } else if (size == storage.length) {
            System.out.println("storage заполнен полностью. резюме не было добавлено");
        } else if (getIndex(resume.getUuid()) < 0) {
            int index = -getIndex(resume.getUuid()) - 1;
            System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = resume;
            size++;
        } else {
            System.out.println("резюме " + resume.getUuid() + " уже существует");
        }
    }


    public void delete(String uuid) {
        if (getIndex(uuid) < 0) {
            System.out.println("такого резюме не существует");
        } else {
            System.arraycopy(storage, getIndex(uuid) + 1,
                    storage, getIndex(uuid), size - (getIndex(uuid) + 1));
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
