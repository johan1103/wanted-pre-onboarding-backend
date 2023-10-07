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
  private String position;
  private String recruitContent;
  private String skills;
  private Integer compensation;
}
