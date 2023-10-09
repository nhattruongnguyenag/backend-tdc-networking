package com.chatapp.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;



public interface FileUploadService {
    List<String> upload(MultipartFile[] files , String type );
    byte[] loadAsResource(String path,String fileName) throws IOException; 
}
