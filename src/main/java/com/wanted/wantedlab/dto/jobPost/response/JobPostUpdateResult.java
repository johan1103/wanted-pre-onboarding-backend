package com.wanted.wantedlab.dto.jobPost.response;

import com.wanted.wantedlab.entity.JobPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
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
