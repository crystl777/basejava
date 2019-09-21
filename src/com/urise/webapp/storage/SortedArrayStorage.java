package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deleteResume(String uuid) {
        System.arraycopy(storage, getIndex(uuid) + 1,
                storage, getIndex(uuid), size - (getIndex(uuid) + 1));
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected void addResume(Resume resume) {
        if (size == 0) {
           saveResume(resume, 0);
        } else {
            int index = -getIndex(resume.getUuid()) - 1;
            System.arraycopy(storage, index, storage, index + 1, size - index);
            saveResume(resume, index);
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
