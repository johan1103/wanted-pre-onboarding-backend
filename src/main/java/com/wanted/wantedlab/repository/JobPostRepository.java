package com.wanted.wantedlab.repository;

import com.wanted.wantedlab.dto.jobPost.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostRepository extends JpaRepository<JobPost,Long> {
}
