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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import com.chatapp.enums.FileType;
import com.chatapp.exception.DuplicateUsernameException;
import com.chatapp.service.FileUploadService;
import com.chatapp.util.EncryptUtils;
import com.chatapp.util.FileUtil;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    ResourceLoader resourceLoader;

    @Override
    public List<String> upload(MultipartFile[] files, String type) {
        try {
            createDirIfNotExist(FileUtil.folderPath);

            List<String> fileNames = new ArrayList<>();
            // read and write the file to the local folder
            Arrays.asList(files).stream().forEach(file -> {
                byte[] bytes = new byte[0];
                try {
                    bytes = file.getBytes();
                    String newFileName = EncryptUtils.createMD5(String.valueOf(System.nanoTime()), EncryptUtils.MD5)
                            + "." + FilenameUtils.getExtension(file.getOriginalFilename());
                    Files.write(Paths.get(FileUtil.folderPath + type + newFileName), bytes);
                    fileNames.add(newFileName);
                } catch (IOException e) {
                    throw new DuplicateUsernameException("upload_failed");
                }
            });

            return fileNames;

        } catch (Exception e) {
            throw e;
        }
    }

    private void createDirIfNotExist(String path) {
        // create directory to save the files
        File directory = new File(path);
        File directoryImage = new File(path + FileType.IMAGE.getName());
        File directoryFile = new File(path + FileType.FILE.getName());
        if (!directory.exists()) {
            directory.mkdir();
            if (!directoryImage.exists() || !directoryFile.exists()) {
                directoryImage.mkdirs();
                directoryFile.mkdirs();
            }
        }
    }

    @Override
    public byte[] loadAsResource(String path, String fileName) throws IOException {
        byte[] image = Files.readAllBytes(Path.of(path + fileName));
        return image;
    }

}
