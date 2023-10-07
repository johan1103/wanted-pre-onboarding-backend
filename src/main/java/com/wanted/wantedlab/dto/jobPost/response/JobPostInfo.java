package com.wanted.wantedlab.dto.jobPost.response;

import com.wanted.wantedlab.dto.jobPost.JobPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JobPostInfo {
  private Long jobPostId;
  private String companyName;
  private String country;
  private String region;
  private String position;
  private Integer compensation;
  private String skills;

  public static JobPostInfo of(JobPost jp){
    return new JobPostInfo(jp.getId(),jp.getCompany().getName(),jp.getCompany().getCountry()
            ,jp.getCompany().getRegion(),jp.getPosition(),jp.getCompensation(),jp.getSkills());
  }
}
