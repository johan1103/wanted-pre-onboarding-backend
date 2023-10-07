package com.wanted.wantedlab.dto.jobPost.response;

import com.wanted.wantedlab.entity.JobPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JobPostUploadResult {
  private Long jobPostId;

  public static JobPostUploadResult of(JobPost jobPost){
    return new JobPostUploadResult(jobPost.getId());
  }
}
