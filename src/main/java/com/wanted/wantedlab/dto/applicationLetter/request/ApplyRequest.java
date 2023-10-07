package com.wanted.wantedlab.dto.applicationLetter.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ApplyRequest {
  private String userId;
  private Long jobPostId;
  private String portfolioUrl;
}
