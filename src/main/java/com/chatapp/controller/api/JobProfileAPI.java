package com.chatapp.controller.api;

import java.util.List;

import com.chatapp.commond.MessageResponseData;
import com.chatapp.dto.request.job_profile.JobApplyProfileRequestDTO;
import com.chatapp.dto.request.job_profile.JobProfileUpdateRequestDTO;
import com.chatapp.dto.response.job_profile.JobProfileManageResponseDTO;
import com.chatapp.dto.response.job_profile.JobProfilePendingResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatapp.commond.ResponseData;
import com.chatapp.service.JobProfileService;

@RestController
@RequestMapping("/api")
public class JobProfileAPI {
    @Autowired
    JobProfileService jobProfileService;

    //////////////////
    //Post
    //////////////////
    @PostMapping({ "job/apply", "job/apply/" })
    public ResponseEntity<ResponseData<String>> applyJobProfile(@RequestBody JobApplyProfileRequestDTO jobApplyProfileRequestDTO) {
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.CREATED, "sucesss",jobProfileService.applyJobProfile(jobApplyProfileRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }

    //////////////////
    //Put
    //////////////////
    @PutMapping({ "job/update", "job/update/" })
    public ResponseEntity<MessageResponseData> updateJobProfile(@RequestBody JobProfileUpdateRequestDTO jobProfileUpdateRequestDTO) {
        boolean isSuccess = jobProfileService.updateJobProfile(jobProfileUpdateRequestDTO);
        if (isSuccess) {
            return ResponseEntity.created(null).body(new MessageResponseData(HttpStatus.CREATED, "update_success"));
        }
        return ResponseEntity.badRequest().body(new MessageResponseData(HttpStatus.BAD_REQUEST, "job_profile_not_exists"));
    }

    //////////////////
    //Get
    //////////////////
    @GetMapping({ "job/post/{postId}", "job/post/{postId}" })
    public ResponseEntity<ResponseData<List<JobProfileManageResponseDTO>>> getListJobProfileByPostId(@PathVariable Long postId) {
        ResponseData<List<JobProfileManageResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",jobProfileService.getJobprofileByPostId(postId));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "job/user/{userId}", "job/user/{userId}" })
    public ResponseEntity<ResponseData<List<JobProfilePendingResponseDTO>>> getListJobProfileByUserId(@PathVariable Long userId) {
        ResponseData<List<JobProfilePendingResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",jobProfileService.getJobprofileByUserId(userId));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "job/{jobId}", "job/{jobId}/" })
    public ResponseEntity<ResponseData<JobProfilePendingResponseDTO>> getJobProfileDetailByPostIdAndJobId(@PathVariable Long jobId) {
        ResponseData<JobProfilePendingResponseDTO> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",jobProfileService.getJobProfileDetail(jobId));
        return ResponseEntity.ok(responseData);
    }

    //////////////////
    //Delete
    //////////////////
    @DeleteMapping({"job/profile/{profileId}"})
    public ResponseEntity<MessageResponseData> removeJobProfile(@PathVariable("profileId") Long profileId) {
        boolean isSuccess = jobProfileService.deleteById(profileId);
        if (isSuccess) {
            return ResponseEntity.ok(new MessageResponseData(HttpStatus.OK, "success"));
        }
        return new ResponseEntity<>(new MessageResponseData(HttpStatus.BAD_REQUEST, "job_profile_not_exists"), HttpStatus.BAD_GATEWAY);
    }
}
