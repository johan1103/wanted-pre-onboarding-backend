package com.wanted.wantedlab.dto.jobPost;

import com.wanted.wantedlab.dto.company.Company;
import com.wanted.wantedlab.dto.jobPost.request.JobPostUpdateRequest;
import com.wanted.wantedlab.dto.jobPost.request.JobPostUploadRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobPost {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String position;
  private String recruitContent;
  private String skills;
  private Integer compensation;
  @ManyToOne(fetch = FetchType.LAZY)
  private Company company;

  public static JobPost createJobPost(JobPostUploadRequest req,Company company){
    return new JobPost(null,req.getPosition(),req.getContent(),req.getSkills(),req.getCompensation(),company);
  }

  public void update(JobPostUpdateRequest updateRequest){
    if(updateRequest.getPosition()!=null)
      this.position=updateRequest.getPosition();
    if(updateRequest.getRecruitContent()!=null)
      this.recruitContent=updateRequest.getRecruitContent();
    if(updateRequest.getSkills()!=null)
      this.skills=updateRequest.getSkills();
    if(updateRequest.getCompensation()!=null)
      this.compensation=updateRequest.getCompensation();
  }
}