package com.test2.cars.configuration;

import com.test2.cars.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UploadStoreConfig implements WebMvcConfigurer {
    final public static String URL_PATH = "/upload-store";

    @Autowired
    private UploadService uploadService;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(String.format("%s/**", URL_PATH))
                .addResourceLocations("file:" + getCorrectUploadStore())
                .setCachePeriod(3600);
    }

    protected String getCorrectUploadStore() {
        // very hard to find hack...
        // when the upload-store absolute path ends with a slash ("/"),
        // spring recognize all files inside this directory
        // otherwise it think it may be a file, so it need to be included
        // in the url
        //
        // we just ensure the provided path ends with a slash

        String path = uploadService.getUploadStore();
        if (! path.endsWith("/")) {
            path = path.concat("/");
        }
        return path;
    }
}

