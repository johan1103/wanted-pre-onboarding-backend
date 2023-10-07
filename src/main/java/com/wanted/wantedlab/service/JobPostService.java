package com.wanted.wantedlab.service;

import com.wanted.wantedlab.dto.company.Company;
import com.wanted.wantedlab.dto.exception.JobPostException;
import com.wanted.wantedlab.dto.exception.JobPostExceptionInfo;
import com.wanted.wantedlab.dto.jobPost.JobPost;
import com.wanted.wantedlab.dto.jobPost.request.JobPostDeleteRequest;
import com.wanted.wantedlab.dto.jobPost.request.JobPostUploadRequest;
import com.wanted.wantedlab.dto.jobPost.response.JobPostDeleteResponse;
import com.wanted.wantedlab.dto.jobPost.response.JobPostUploadResult;
import com.wanted.wantedlab.repository.CompanyRepository;
import com.wanted.wantedlab.repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  public JobPostDeleteResponse delete(JobPostDeleteRequest deleteRequest){
    return null;
  }
  public Company validateCompany(Long companyId){
    return companyRepository.findById(companyId)
          .orElseThrow(()->new JobPostException("invalid company id"
                  , JobPostExceptionInfo.INVALID_COMPANY, HttpStatus.BAD_REQUEST));
  }
}
