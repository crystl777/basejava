package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serializer.StreamSerializer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;
    private StreamSerializer serializationStrategy;


    protected PathStorage(String dir, StreamSerializer serializationStrategy) {
        this.serializationStrategy = serializationStrategy;
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    public void clear() {
        fileList(directory).forEach(this::deleteResume);
    }

    @Override
    public int size() {
        return (int) fileList(directory).count();
    }


    @Override
    protected void addResume(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create file", path.toString(), e);
        }
        updateResume(resume, path);
    }

    @Override
    protected void updateResume(Resume resume, Path path) {
        try {
            serializationStrategy.writeResume(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("File write error", path.toString(), e);
        }
    }


    @Override
    protected void deleteResume(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("delete is not successful", path.toString());
        }
    }


    @Override
    protected Resume getResume(Path path) {
        try {
            return serializationStrategy.readResume(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("IO error", path.toString(), e);
        }
    }


    @Override
    protected boolean isExist(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }


    @Override
    protected List<Resume> getStorageList() {
        return fileList(directory).map(this::getResume).collect(Collectors.toList());
    }

    private Stream<Path> fileList(Path directory) {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("directory is falling", directory.toString());
        }
    }
}
