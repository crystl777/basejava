package com.urise.webapp;

import com.urise.webapp.storage.SqlStorage;
import com.urise.webapp.storage.Storage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final String PROPS =  "/resumes.properties";
    private static final Config INSTANCE = new Config();
    private final File STORAGE_DIR;
    private final Storage STORAGE;

    public static Config get() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream is = Config.class.getResourceAsStream(PROPS)) {
            Properties props = new Properties();
            props.load(is);
            STORAGE_DIR = new File(props.getProperty("storage.dir"));
            STORAGE = new SqlStorage(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS);
        }
    }

    public File getStorageDir() {
        return STORAGE_DIR;
    }

    public Storage getStorage() {
        return STORAGE;
    }
}