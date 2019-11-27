package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
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
        try {
            File[] allContents = directory.listFiles();
            for (File file : Objects.requireNonNull(allContents)) {
                file.delete();
            }
        } catch (NullPointerException e) {
            throw new StorageException("directory is null", directory.getName(), e);
        }
    }

    @Override
    public int size() {
        try {
            return Objects.requireNonNull(directory.listFiles()).length;
        } catch (NullPointerException e) {
            throw new StorageException("directory is null", directory.getName(), e);
        }
    }

    @Override
    protected void addResume(Resume resume, File file) {
        try {
            file.createNewFile();
            writeResume(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void updateResume(Resume resume, File file) {
        try {
            writeResume(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }


    @Override
    protected void deleteResume(File file) {
        file.delete();
    }


    @Override
    protected Resume getResume(File file) {
        try {
            return readResume(file);
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
        try {
            File[] files = directory.listFiles();
            List<Resume> fileList = new ArrayList<>();
            for (File file : Objects.requireNonNull(files)) {
                fileList.add(getResume(file));
            }
            return fileList;
        } catch (NullPointerException e) {
            throw new StorageException("directory is null", directory.getName(), e);
        }
    }

    protected abstract void writeResume(Resume resume, File file) throws IOException;

    protected abstract Resume readResume(File file) throws IOException;
}
