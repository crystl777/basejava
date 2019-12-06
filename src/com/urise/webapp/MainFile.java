package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/com/urise/webapp");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        printDirectoryDeeply("./src/com/urise/webapp", recursionDepth(dir));
    }


    //подсчёт глубины рекурсии
    private static int recursionDepth(File dir) {

        File[] files = dir.listFiles();
        int depth = 0;

        for (File file : files) {
            if (file.isDirectory()) {
                depth++;
                recursionDepth(file);
            }
        }
        return depth;
    }


    public static void printDirectoryDeeply(String dirName, int depth) {
        File dir = new File(dirName);
        if (!dir.isDirectory()) {
            return;
        }
        StringBuilder space = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            space.append(' ');
        }
        File files[] = dir.listFiles();
        for (File file : files) {
            System.out.println(space.toString() + file.getName());
            if (file.isDirectory()) {
                printDirectoryDeeply(file.getPath(), depth + 1);
            }
        }
    }
}
