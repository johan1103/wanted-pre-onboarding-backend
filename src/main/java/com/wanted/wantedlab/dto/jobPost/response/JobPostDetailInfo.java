package com.wanted.wantedlab.dto.jobPost.response;

import com.wanted.wantedlab.entity.JobPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JobPostDetailInfo {
  private Long jobPostId;
  private String companyName;
  private String country;
  private String region;
  private String position;
  private Integer compensation;
  private String skills;
  private String recruitContent;
  private List<CompanyJobPostInfo> companyOtherJobPosts;

  public static JobPostDetailInfo of(JobPost jp, List<JobPost> jobPosts){
    List<CompanyJobPostInfo> companyJobPosts = jobPosts.stream()
            .filter((j)-> !Objects.equals(j.getId(), jp.getId()))
            .map(CompanyJobPostInfo::of).toList();
    return new JobPostDetailInfo(jp.getId(),jp.getCompany().getName(),jp.getCompany().getCountry()
            ,jp.getCompany().getRegion(),jp.getPosition(),jp.getCompensation(),jp.getSkills(),jp.getRecruitContent()
            ,companyJobPosts);
  }
}
