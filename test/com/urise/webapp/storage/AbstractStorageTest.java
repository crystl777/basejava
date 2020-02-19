package com.urise.webapp.storage;

import com.urise.webapp.Config;
import com.urise.webapp.ResumeTestData;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public abstract class AbstractStorageTest {

    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected Storage storage;

    //uuid - минимум 36 знаков
    private static final String UUID_1 = "UUID_1                              ";
    private static final String UUID_2 = "UUID_2                              ";
    private static final String UUID_3 = "UUID_3                              ";
    private static final String UUID_4 = "UUID_4                              ";


    private static final Resume resume1 = new Resume(UUID_1, "Name1");
    private static final Resume resume2 = new Resume(UUID_2, "Name2");
    private static final Resume resume3 = new Resume(UUID_3, "Name3");
    private static final Resume resume4 = new Resume(UUID_4, "Name4");


    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resume1);
        ResumeTestData.addContactsResume(resume1);
        ResumeTestData.addSectionsResume(resume1);

        storage.save(resume2);
        ResumeTestData.addContactsResume(resume2);
        ResumeTestData.addSectionsResume(resume2);

        storage.save(resume3);
        ResumeTestData.addContactsResume(resume3);
        ResumeTestData.addSectionsResume(resume3);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }


    @Test
    public void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        List<Resume> listWithSort = new ArrayList<>();
        listWithSort.add(resume1);
        listWithSort.add(resume2);
        listWithSort.add(resume3);
        Collections.sort(listWithSort);
        assertEquals(3, list.size());
        assertEquals(listWithSort, list);
    }


    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "New Name");
        ResumeTestData.addContactsResume(newResume);
        ResumeTestData.addSectionsResume(resume1);
        storage.update(newResume);
        Assert.assertEquals(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void save() {
        storage.save(resume4);
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(resume1);
    }

    @Test
    public void get() {
        Assert.assertEquals(resume1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }
}