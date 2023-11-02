package com.chatapp.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatapp.commond.ResponseData;
import com.chatapp.dto.request.JobApplyProfileRequestDTO;
import com.chatapp.dto.response.JobProfileResponseDTO;
import com.chatapp.service.JobProfileService;

@RestController
@RequestMapping("/api")
public class JobProfileAPI {
    @Autowired
    JobProfileService jobProfileService;

    @PostMapping({ "job/apply", "job/apply/" })
    public ResponseEntity<ResponseData<String>> applyJobProfile(@RequestBody JobApplyProfileRequestDTO jobApplyProfileRequestDTO) {
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",jobProfileService.applyJobProfile(jobApplyProfileRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }

    @GetMapping({ "job/post/{postId}", "job/post/{postId}" })
    public ResponseEntity<ResponseData<List<JobProfileResponseDTO>>> getListJobProfileByPostId(@PathVariable Long postId) {
        ResponseData<List<JobProfileResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",jobProfileService.getJobprofileByPostId(postId));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "job/{jobId}", "job/{jobId}/" })
    public ResponseEntity<ResponseData<JobProfileResponseDTO>> getJobProfileDetailByPostIdAndJobId(@PathVariable Long jobId) {
        ResponseData<JobProfileResponseDTO> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",jobProfileService.getJobProfileDetail(jobId));
        return ResponseEntity.ok(responseData);
    }
}
