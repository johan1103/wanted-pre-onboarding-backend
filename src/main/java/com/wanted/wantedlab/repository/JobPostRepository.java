package com.wanted.wantedlab.repository;

import com.wanted.wantedlab.dto.jobPost.JobPost;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobPostRepository extends JpaRepository<JobPost,Long> {
  @Query("select j from JobPost j join fetch j.company")
  Slice<JobPost> getJobPostSlice(PageRequest pageRequest);
  @Query("select j from JobPost j join fetch j.company c where c.id=:company_id")
  List<JobPost> getJobPostsByCompanyId(PageRequest pageRequest, @Param("company_id")Long companyId);
}
