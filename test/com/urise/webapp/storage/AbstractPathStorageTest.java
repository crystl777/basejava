package com.urise.webapp.storage;

import static org.junit.Assert.*;

public class AbstractPathStorageTest extends AbstractStorageTest {

    public AbstractPathStorageTest() {
        super(new AbstractPathStorage(STORAGE_DIR.getPath(), new ObjectStreamStorage()));
    }
}