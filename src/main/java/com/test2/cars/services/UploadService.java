package com.test2.cars.services;

import com.test2.cars.configuration.UploadStoreConfig;
import com.test2.cars.exceptions.UploadProcessingException;
import com.test2.cars.utils.AppUtils;
import com.test2.cars.utils.FileUtils;
import com.test2.cars.utils.MultipartFileWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadService {
    @Value("${upload_store.cars.path}")
    private String uploadStore;

    @PostConstruct
    public void ensureStoreExists() {
        FileUtils.createDir(getUploadStore());
    }

    public String store(MultipartFile file) throws UploadProcessingException {
        MultipartFileWrapper wrapper = new MultipartFileWrapper(file);
        try {
            return doStore(wrapper);
        } catch (IOException exception) {
            throw new UploadProcessingException(wrapper, exception);
        }
    }

    public String getUploadStore() {
        return uploadStore;
    }

    protected String doStore(MultipartFileWrapper wrapper) throws IOException {
        // find some random file to be stored
        File availFile = FileUtils.findAvailableFile(getUploadStore());

        // join the random file with original extension
        Path fullPath = new File(
                availFile.toString() + wrapper.getExtension()
        ).toPath();

        // transfer the uploaded file to disk store
        wrapper.getBaseFile().transferTo(fullPath);

        // join the base url path with new file name
        Path httpPath = Paths.get(
                UploadStoreConfig.URL_PATH,
                fullPath.getFileName().toString()
        );

        return AppUtils.getUrl(httpPath.toString());
    }
}
