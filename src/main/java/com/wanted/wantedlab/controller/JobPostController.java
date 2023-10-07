package com.wanted.wantedlab.controller;

import com.wanted.wantedlab.dto.jobPost.request.JobPostDeleteRequest;
import com.wanted.wantedlab.dto.jobPost.request.JobPostUpdateRequest;
import com.wanted.wantedlab.dto.jobPost.request.JobPostUploadRequest;
import com.wanted.wantedlab.dto.jobPost.response.*;
import com.wanted.wantedlab.service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class JobPostController {
  private final JobPostService jobPostService;
  @PostMapping("/job-post")
  public JobPostUploadResult uploadJobPost(@RequestBody @Valid JobPostUploadRequest uploadRequest){
    return jobPostService.upload(uploadRequest);
  }
  @PutMapping("/job-post")
  public JobPostUpdateResult updateJobPost(@RequestBody @Valid JobPostUpdateRequest updateRequest){
    return jobPostService.update(updateRequest);
  }
  @DeleteMapping("/job-post")
  public JobPostDeleteResult deleteJobPost(@RequestBody JobPostDeleteRequest deleteRequest){
    return jobPostService.delete(deleteRequest);
  }
  @GetMapping("/job-post/list")
  public JobPostInfoList getJobPosts(@RequestParam("page")int page,@RequestParam("size")int size){
    return jobPostService.getJobPosts(page,size);
  }
  @GetMapping("/job-post/detail")
  public JobPostDetailInfo getJobPostDetail(@RequestParam("job-post-id")Long jobPostId){
    return jobPostService.getJobPostDetail(jobPostId);
  }
}
