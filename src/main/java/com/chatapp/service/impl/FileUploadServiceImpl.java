package com.chatapp.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import com.chatapp.constant.SystemConstant;
import com.chatapp.enums.FileType;
import com.chatapp.exception.DuplicateUsernameException;
import com.chatapp.service.FileUploadService;
import com.chatapp.util.EncryptUtils;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    ResourceLoader resourceLoader;

    @Override
    public List<String> upload(MultipartFile[] files, String type) throws IOException{
        try {
            createDirIfNotExist(SystemConstant.FILE_PATH_ORIGIN + type);

            List<String> fileNames = new ArrayList<>();
            // read and write the file to the local folder
            Arrays.asList(files).stream().forEach(file -> {
                byte[] bytes = new byte[0];
                try {
                    bytes = file.getBytes();
                    String newFileName = EncryptUtils.createMD5(String.valueOf(System.nanoTime()), EncryptUtils.MD5)
                            + "." + FilenameUtils.getExtension(file.getOriginalFilename());
                    Files.write(Paths.get(SystemConstant.FILE_PATH_ORIGIN + type + newFileName), bytes);
                    fileNames.add(newFileName);
                } catch (IOException e) {
                    throw new DuplicateUsernameException("upload_failed");
                }
            });

            return fileNames;

        } catch (IOException e) {
            throw new IOException("upload_fail");
        }
    }

    private void createDirIfNotExist(String path) throws IOException{
        // create directory to save the files
        File directory = new File(path);
        if (!directory.exists()) {
            FileUtils.forceMkdir(directory);
        }
    }

    @Override
    public byte[] loadAsResource(String path, String fileName) throws IOException {
        byte[] image = Files.readAllBytes(Path.of(path + fileName));
        return image;
    }

}
