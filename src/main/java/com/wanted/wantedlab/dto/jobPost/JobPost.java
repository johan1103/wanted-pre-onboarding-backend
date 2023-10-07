package com.wanted.wantedlab.dto.jobPost;

import com.wanted.wantedlab.dto.company.Company;
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
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
}