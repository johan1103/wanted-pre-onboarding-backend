package com.wanted.wantedlab.repository;

import com.wanted.wantedlab.entity.ApplicationLetter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationLetterRepository extends JpaRepository<ApplicationLetter,Long> {
  @Modifying
  @Query("delete from ApplicationLetter al where al.id in :letter_ids")
  void deleteLetterAssociatedJobPost(@Param("letter_ids")List<Long> letterIds);

  @Query("select al from ApplicationLetter al join fetch al.jobPost j join fetch j.company c join fetch al.user u " +
          "where j.id=:job_post_id")
  List<ApplicationLetter> findDeleteApplicationLetter(@Param("job_post_id")Long jobPostId);
}
