package com.wanted.wantedlab.service;

import com.wanted.wantedlab.dto.exception.JobPostException;
import com.wanted.wantedlab.dto.exception.JobPostExceptionInfo;
import com.wanted.wantedlab.dto.exception.UserException;
import com.wanted.wantedlab.dto.exception.UserExceptionInfo;
import com.wanted.wantedlab.entity.Company;
import com.wanted.wantedlab.entity.JobPost;
import com.wanted.wantedlab.entity.User;
import com.wanted.wantedlab.repository.CompanyRepository;
import com.wanted.wantedlab.repository.JobPostRepository;
import com.wanted.wantedlab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityValidator {
  private final JobPostRepository jobPostRepository;
  private final CompanyRepository companyRepository;
  private final UserRepository userRepository;
  public JobPost validateJobPost(Long jobPostId){
    return jobPostRepository.findById(jobPostId)
            .orElseThrow(()->new JobPostException("invalid job post id"
                    , JobPostExceptionInfo.INVALID_JOBPOSTID, HttpStatus.BAD_REQUEST));
  }
  public Company validateCompany(Long companyId){
    return companyRepository.findById(companyId)
            .orElseThrow(()->new JobPostException("invalid company id"
                    , JobPostExceptionInfo.INVALID_COMPANY, HttpStatus.BAD_REQUEST));
  }
  public User validateUser(Long userId){
    return userRepository.findById(userId)
            .orElseThrow(()->new UserException("invalid user id"
                    , UserExceptionInfo.INVALID_USERID, HttpStatus.BAD_REQUEST));
  }
}
