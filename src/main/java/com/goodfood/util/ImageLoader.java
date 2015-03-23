package com.goodfood.util;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

@Component
public class ImageLoader {

    @Autowired
    private ServletContext servletContext;

    public String saveImage(String filename,LoadDir loadDir, MultipartFile image)
            throws RuntimeException, IOException {
        try {
            String path = "/resources/upload/" +loadDir.toString().toLowerCase()+"/";
            File file = new File(servletContext.getRealPath("/") + path
                    + filename);
            FileUtils.writeByteArrayToFile(file, image.getBytes());
            return file.getCanonicalPath();
        } catch (IOException e) {
            throw e;
        }
    }
}


