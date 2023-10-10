package com.chatapp.controller.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.chatapp.commond.ResponseData;
import com.chatapp.enums.FileType;
import com.chatapp.service.FileUploadService;
import com.chatapp.util.FileUtil;


@RestController
@RequestMapping("/api")
public class UploadAPI {

    @Autowired
    FileUploadService fileUploadService;

    @PostMapping({ "/upload/images", "upload/images/" })
    public ResponseEntity<ResponseData<List<String>>> uploadImages(@RequestParam("files") MultipartFile[] files) {
        ResponseData<List<String>> responseData = new ResponseData<List<String>>(HttpStatus.OK, "success",
                fileUploadService.upload(files, FileType.IMAGE.getName()));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "/upload/files", "upload/files/" })
    public ResponseEntity<ResponseData<List<String>>> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        ResponseData<List<String>> responseData = new ResponseData<List<String>>(HttpStatus.OK, "success",
                fileUploadService.upload(files, FileType.FILE.getName()));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "/images/{name}", "/images/{name}/" })
    public ResponseEntity<byte[]> identificationImage(@PathVariable String name) throws IOException{
        byte[] image = fileUploadService.loadAsResource(FileUtil.folderPath + FileType.IMAGE.getName(), name);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }
}