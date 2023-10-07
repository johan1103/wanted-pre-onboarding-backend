package com.wanted.wantedlab.dto.jobPost.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JobPostUploadRequest {
  @NotEmpty
  private String position;
  @NotBlank
  private String content;
  @NotEmpty
  private String skills;
  private Integer compensation;
  @NotNull
  private Long companyId;
}
