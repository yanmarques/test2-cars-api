package com.test2.cars.utils;

import org.springframework.web.multipart.MultipartFile;

public class MultipartFileWrapper {
    final private MultipartFile baseFile;
    final private String fileName;
    final private String extension;

    public MultipartFileWrapper(MultipartFile baseFile) {
        this.baseFile = baseFile;
        this.fileName = gatherName();
        this.extension = FileUtils.getExtension(this.fileName);
    }

    public MultipartFile getBaseFile() {
        return baseFile;
    }

    public String getExtension() {
        return extension;
    }

    public String getFileName() {
        return fileName;
    }

    protected String gatherName() {
        if (getBaseFile().getOriginalFilename() == null) {
            return getBaseFile().getName();
        }

        return getBaseFile().getOriginalFilename();
    }
}
