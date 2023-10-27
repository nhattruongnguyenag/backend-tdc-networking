package com.chatapp.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatapp.commond.ResponseData;
import com.chatapp.dto.request.JobApplyProfileRequestDTO;
import com.chatapp.service.JobProfileService;

@RestController
@RequestMapping("/api")
public class JobProfileAPI {
    @Autowired
    JobProfileService jobProfileService;

    @PostMapping({ "job/apply", "job/apply/" })
    public ResponseEntity<ResponseData<String>> applyJobProfile(@RequestBody JobApplyProfileRequestDTO jobApplyProfileRequestDTO) {
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.CREATED, "sucesss",jobProfileService.applyJobProfile(jobApplyProfileRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }
}
