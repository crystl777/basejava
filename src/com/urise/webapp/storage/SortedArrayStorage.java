package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        if (!isExist(resume.getUuid()) && !isFull()) {
            System.out.println("сохраняем новое резюме");
            addResume(resume);
        }
    }

    @Override
    protected void deleteResume(Resume resume) {
        if (isExist(resume.getUuid())) {
            delete(resume.getUuid());
        }
    }

    @Override
    protected void addResume(Resume resume) {
        if (size == 0) {
            storage[0] = resume;
            size++;
        } else {
            int index = -getIndex(resume.getUuid()) - 1;
            System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = resume;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        System.arraycopy(storage, getIndex(uuid) + 1,
                storage, getIndex(uuid), size - (getIndex(uuid) + 1));
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
