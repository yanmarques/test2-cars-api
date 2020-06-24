package com.test2.cars.exceptions;

import com.test2.cars.utils.MultipartFileWrapper;
import org.apache.tomcat.util.http.fileupload.FileUploadException;

public class UploadProcessingException extends FileUploadException {
    final private MultipartFileWrapper uploadedFile;

    public UploadProcessingException(
            MultipartFileWrapper uploadedFile,
            String message,
            Throwable cause
    ) {
        super(message, cause);
        this.uploadedFile = uploadedFile;
    }

    public UploadProcessingException(MultipartFileWrapper uploadedFile, Throwable cause) {
        this(
                uploadedFile,
                cause.getMessage(),
                cause
        );
    }

    public MultipartFileWrapper getUploadedFile() {
        return uploadedFile;
    }
}
