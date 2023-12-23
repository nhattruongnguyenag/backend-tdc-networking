package com.chatapp.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;



public interface FileUploadService {
    List<String> upload(MultipartFile[] files , String type ) throws IOException;
    byte[] loadAsResource(String fileName) throws IOException;
    Resource loadFileAsResource(String fileName);
}
