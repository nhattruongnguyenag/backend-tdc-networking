package com.chatapp.controller.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.chatapp.commond.ResponseData;
import com.chatapp.enums.FileType;
import com.chatapp.service.FileUploadService;

@RestController
@RequestMapping("/api")
public class UploadAPI {

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    ResourceLoader resourceLoader;

    //////////////////
    //Post
    //////////////////
    @PostMapping({ "/upload/images", "upload/images/" })
    public ResponseEntity<ResponseData<List<String>>> uploadImages(@RequestParam("files") MultipartFile[] files)
            throws IOException {
        ResponseData<List<String>> responseData = new ResponseData<List<String>>(HttpStatus.OK, "success",
                fileUploadService.upload(files, FileType.IMAGE.getName()));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "/upload/files", "upload/files/" })
    public ResponseEntity<ResponseData<List<String>>> uploadFiles(@RequestParam("files") MultipartFile[] files)
            throws IOException {
        ResponseData<List<String>> responseData = new ResponseData<List<String>>(HttpStatus.OK, "success",
                fileUploadService.upload(files, FileType.FILE.getName()));
        return ResponseEntity.ok(responseData);
    }

    //////////////////
    //Get
    //////////////////
    @GetMapping({ "/images/{name}", "/images/{name}/" })
    public ResponseEntity<byte[]> identificationImage(@PathVariable String name) throws IOException {
        byte[] image = fileUploadService.loadAsResource(name);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }

    @GetMapping(value = { "/files/{name}", "/files/{name}/" }, produces = "application/pdf")
    public ResponseEntity<Resource> getFile(@PathVariable("name") String fileName) {
        Resource resource = fileUploadService
                .loadFileAsResource(fileName);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Disposition", "attachment");
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(resource);
    }
}