package com.wanted.wantedlab.dto.jobPost.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CompanyJobPostInfo {
  private String jobPostId;
  private String position;
  private String skills;
}
