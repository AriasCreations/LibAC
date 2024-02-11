package dev.zontreck.ariaslib.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileIO
{

    public static String readFile(String filePath) {
        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
            return new String(fileBytes);
        } catch (IOException e) {
            return "An error occurred: " + e.getMessage();
        }
    }
    public static void writeFile(String filePath, String newContent) {
        try {
            Files.write(Paths.get(filePath), newContent.getBytes());
        } catch (IOException e) {
        }
    }


    /**
     * Recursively delete a directory
     * @param directory The folder to delete
     */
    public static void deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        file.delete();
                    }
                }
            }
            // Now directory is empty, so delete it
            directory.delete();
            System.out.println("Directory deleted: " + directory.getAbsolutePath());
        } else {
            System.out.println("Directory does not exist: " + directory.getAbsolutePath());
        }
    }
}

