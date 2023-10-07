package com.wanted.wantedlab.dto.jobPost;

import com.wanted.wantedlab.dto.company.Company;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
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
}