package com.wanted.wantedlab.dto.jobPost.response;

import com.wanted.wantedlab.dto.jobPost.JobPost;
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

  public static JobPostUpdateResult of(JobPost jp){
    return new JobPostUpdateResult(jp.getId(),jp.getPosition(),jp.getRecruitContent()
            ,jp.getSkills(),jp.getCompensation());
  }
}
