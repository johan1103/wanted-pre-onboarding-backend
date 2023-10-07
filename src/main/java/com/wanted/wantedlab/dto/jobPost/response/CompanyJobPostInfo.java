package com.wanted.wantedlab.dto.jobPost.response;

import com.wanted.wantedlab.entity.JobPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CompanyJobPostInfo {
  private Long jobPostId;
  private String position;
  private String skills;

  public static CompanyJobPostInfo of(JobPost jobPost){
    return new CompanyJobPostInfo(jobPost.getId(),jobPost.getPosition(),jobPost.getSkills());
  }
}
