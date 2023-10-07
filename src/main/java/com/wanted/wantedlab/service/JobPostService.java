package com.wanted.wantedlab.service;

import com.wanted.wantedlab.entity.Company;
import com.wanted.wantedlab.dto.exception.JobPostException;
import com.wanted.wantedlab.dto.exception.JobPostExceptionInfo;
import com.wanted.wantedlab.entity.JobPost;
import com.wanted.wantedlab.dto.jobPost.request.JobPostDeleteRequest;
import com.wanted.wantedlab.dto.jobPost.request.JobPostUploadRequest;
import com.wanted.wantedlab.dto.jobPost.response.*;
import com.wanted.wantedlab.dto.jobPost.request.JobPostUpdateRequest;
import com.wanted.wantedlab.repository.CompanyRepository;
import com.wanted.wantedlab.repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JobPostService {
  private final JobPostRepository jobPostRepository;
  private final CompanyRepository companyRepository;
  @Transactional
  public JobPostUploadResult upload(JobPostUploadRequest postUploadRequest){
    Company uploadCompany = validateCompany(postUploadRequest.getCompanyId());
    JobPost newJobPost = JobPost.createJobPost(postUploadRequest,uploadCompany);

    JobPost createdJobPost = jobPostRepository.save(newJobPost);
    return JobPostUploadResult.of(createdJobPost);
  }
  @Transactional
  public JobPostDeleteResult delete(JobPostDeleteRequest deleteRequest) {
    JobPost jobPost = validateJobPost(deleteRequest.getJobPostId());
    jobPostRepository.delete(jobPost);
    return new JobPostDeleteResult(true);
  }
  @Transactional
  public JobPostUpdateResult update(JobPostUpdateRequest updateRequest){
    JobPost jobPost = validateJobPost(updateRequest.getId());
    jobPost.update(updateRequest);
    return JobPostUpdateResult.of(jobPost);
  }
  public JobPost validateJobPost(Long jobPostId){
    return jobPostRepository.findById(jobPostId)
            .orElseThrow(()->new JobPostException("invalid job post id"
                    , JobPostExceptionInfo.INVALID_JOBPOSTID,HttpStatus.BAD_REQUEST));
  }
  public Company validateCompany(Long companyId){
    return companyRepository.findById(companyId)
          .orElseThrow(()->new JobPostException("invalid company id"
                  , JobPostExceptionInfo.INVALID_COMPANY, HttpStatus.BAD_REQUEST));
  }

  public JobPostInfoList getJobPosts(int page,int size){
    PageRequest pageRequest = PageRequest.of(page,size);
    Slice<JobPost> jobPostSlice = jobPostRepository.getJobPostSlice(pageRequest);
    return JobPostInfoList.of(jobPostSlice);
  }
  public JobPostDetailInfo getJobPostDetail(Long jobPostId){
    JobPost jobPost = validateJobPost(jobPostId);
    PageRequest pageRequest = PageRequest.of(0,5);
    List<JobPost> companyJobPosts = jobPostRepository.getJobPostsByCompanyId(pageRequest,jobPost.getCompany().getId());
    return JobPostDetailInfo.of(jobPost,companyJobPosts);
  }
}
