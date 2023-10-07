package com.wanted.wantedlab.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeletedApplicationLetter {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String jobPostPosition;
  private String companyName;
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  public static DeletedApplicationLetter of(ApplicationLetter al){
    return new DeletedApplicationLetter(null,al.getJobPost().getPosition(),al.getJobPost().getCompany().getName(),
            al.getUser());
  }
}
