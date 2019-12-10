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


        File testFile = new File("./src/com/urise/webapp");
        printDirectory(testFile, "");
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


    //рекурсивный вывод каталогов и файлов с отступами
    public static void printDirectory(File file, String indent) {

        File[] fileList = file.listFiles();
        if (fileList != null) {
            for (File f : fileList) {
                if (f.isFile()) {
                    System.out.println(indent + f.getName());
                } else if (f.isDirectory()) {
                    System.out.println(indent + f.getName());
                    printDirectory(f, "  " + indent);
                }
            }
        }
    }
}
