package com.chatapp.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.dto.request.ExampleRequestDTO;
import com.chatapp.service.ExampleService;

@RestController
@RequestMapping("/api/examples")
public class ExampleAPI {
    @Autowired
    ExampleService exampleService;

    @PostMapping({ "/save", "/save/" })
    public ResponseEntity<String> save(@RequestBody ExampleRequestDTO exampleRequestDTO) {
        return ResponseEntity.ok(exampleService.save(exampleRequestDTO));
    }
}
