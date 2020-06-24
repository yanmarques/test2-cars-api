package com.test2.cars.utils;

import net.bytebuddy.utility.RandomString;

import java.io.File;

public class FileUtils {
    public static File findAvailableFile(String directory) {
        return findAvailableFile(directory, 12);
    }

    public static File findAvailableFile(String directory, int randLength) {
        File someFile;

        // ensure we get an valid non-existent file
        do {
            someFile = new File(directory, RandomString.make(randLength));
        } while (someFile.exists());

        return someFile;
    }

    public static void createDir(String path) {
        new File(path).mkdir();

        if (! new File(path).exists()) {
            throw new IllegalStateException(
                    String.format("Failed to create directory at: %s", path)
            );
        }
    }

    public static String getExtension(String fileName) {
        String extension = "";

        if (fileName.contains(".")) {
            extension = fileName.substring(fileName.lastIndexOf("."));

            // discard invalid extensions
            if (extension.length() == 1) {
                extension = "";
            }
        }

        return extension;
    }
}
