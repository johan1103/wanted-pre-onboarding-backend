package com.wanted.wantedlab.dto.jobPost.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JobPostDetailInfo {
  private Long jobPostId;
  private String companyName;
  private String country;
  private String region;
  private String position;
  private Integer compensation;
  private String skills;
  private List<CompanyJobPostInfo> companyOtherJobPosts;
}
