package com.wanted.wantedlab.dto.jobPost.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JobPostUploadRequest {
  private String position;
  private String content;
  private String skills;
  private Integer compensation;
  @NotNull
  private Long companyId;
}
