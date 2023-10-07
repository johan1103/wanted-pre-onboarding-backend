package com.wanted.wantedlab.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class ApplicationLetter {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  private JobPost jobPost;
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;
  private String portfolioLink;
}
