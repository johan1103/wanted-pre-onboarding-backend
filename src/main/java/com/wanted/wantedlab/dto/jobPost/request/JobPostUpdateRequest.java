package com.wanted.wantedlab.dto.jobPost.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JobPostUpdateRequest {
  @NotNull
  private Long id;
  @NotEmpty
  private String position;
  @NotEmpty
  private String recruitContent;
  @NotEmpty
  private String skills;
  @NotEmpty
  private Integer compensation;
}
