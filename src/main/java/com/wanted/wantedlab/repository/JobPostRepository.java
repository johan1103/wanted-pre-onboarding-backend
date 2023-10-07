package com.wanted.wantedlab.repository;

import com.wanted.wantedlab.dto.jobPost.JobPost;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobPostRepository extends JpaRepository<JobPost,Long> {
  @Query("select j from JobPost j")
  Slice<JobPost> getJobPostSlice(PageRequest pageRequest);
}
