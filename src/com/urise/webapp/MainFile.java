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

        //рекурсивный обход всех файлов и подкаталогов внутри заданного каталога
        try {
            recursiveBypass(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        //метод для рекурсивного обхода файлов и каталогов
    private static void recursiveBypass(File dir) throws IOException {
        File[] files = dir.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                System.out.println(file.getPath());
            } else {
                recursiveBypass(file);
            }
        }
        System.out.println(dir.getPath());
    }
}
