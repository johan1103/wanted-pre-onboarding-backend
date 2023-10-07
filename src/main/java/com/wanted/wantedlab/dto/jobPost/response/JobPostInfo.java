package com.wanted.wantedlab.dto.jobPost.response;

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
  
}
