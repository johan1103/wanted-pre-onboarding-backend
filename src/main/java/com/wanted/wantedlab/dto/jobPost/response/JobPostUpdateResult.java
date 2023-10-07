package com.wanted.wantedlab.dto.jobPost.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class JobPostUpdateResult {
  private Long id;
  private String position;
  private String recruitContent;
  private String skills;
  private Integer compensation;
  private String companyId;
}
