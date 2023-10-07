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
  /**
   *
   id        int auto_increment
   primary key,
   job_post_position varchar(255) not null,
   company_name    varchar(255) not null,
   user_id varchar(255) not null,
   portfolio_link varchar(45)  not null,
   constraint fk_user
   foreign key (user_id) references user (id)
   */
}
