package com.wanted.wantedlab.controller;

import com.wanted.wantedlab.dto.jobPost.request.JobPostUploadRequest;
import com.wanted.wantedlab.dto.jobPost.response.JobPostUploadResult;
import com.wanted.wantedlab.service.JobPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JobPostController {
  private final JobPostService jobPostService;
  @PostMapping("/job")
  public JobPostUploadResult uploadJobPost(@RequestBody JobPostUploadRequest uploadRequest){
    return jobPostService.upload(uploadRequest);
  }
}
