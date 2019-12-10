package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private File directory;
    private SerializationStrategy serializationStrategy;


    protected FileStorage(File directory, SerializationStrategy serializationStrategy) {
        this.serializationStrategy = serializationStrategy;
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    public void clear() {
        for (File file : fileList(directory)) {
            deleteResume(file);
        }
    }

    @Override
    public int size() {
        return fileList(directory).length;
    }

    @Override
    protected void addResume(Resume resume, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file", file.getName(), e);
        }
        updateResume(resume, file);
    }

    @Override
    protected void updateResume(Resume resume, File file) {
        try {
            serializationStrategy.writeResume(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File write error", resume.getUuid(), e);
        }
    }


    @Override
    protected void deleteResume(File file) {
        if (!file.delete()) {
            throw new StorageException("delete is not successful", file.getName());
        }
    }


    @Override
    protected Resume getResume(File file) {
        try {
            return serializationStrategy.readResume(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }


    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }


    @Override
    protected List<Resume> getStorageList() {
        List<Resume> fileList = new ArrayList<>();
        for (File file : fileList(directory)) {
            fileList.add(getResume(file));
        }
        return fileList;
    }

    private File[] fileList (File directory) {
        File[] allContents = directory.listFiles();
        if (allContents == null) {
            throw new StorageException("directory is null", directory.getName());
        } else return allContents;
    }
}
