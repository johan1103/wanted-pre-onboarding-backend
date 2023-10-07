package com.wanted.wantedlab.entity;

import com.wanted.wantedlab.dto.applicationLetter.request.ApplyRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationLetter {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  private JobPost jobPost;
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;
  private String portfolioLink;

  public static ApplicationLetter createApplicationLetter(ApplyRequest applyRequest,User user,JobPost jobPost){
    return new ApplicationLetter(null,jobPost,user,applyRequest.getPortfolioUrl());
  }
}
