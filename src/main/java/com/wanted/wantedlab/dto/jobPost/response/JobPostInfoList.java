package com.wanted.wantedlab.dto.jobPost.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JobPostInfoList {
  private Integer page;
  private Integer size;
  private Boolean hasNext;
  private List<JobPostInfo> jobPosts;
}
