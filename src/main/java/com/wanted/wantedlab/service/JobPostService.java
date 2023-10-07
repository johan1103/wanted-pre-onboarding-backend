package com.wanted.wantedlab.service;

import com.wanted.wantedlab.entity.ApplicationLetter;
import com.wanted.wantedlab.entity.Company;
import com.wanted.wantedlab.dto.exception.JobPostException;
import com.wanted.wantedlab.dto.exception.JobPostExceptionInfo;
import com.wanted.wantedlab.entity.DeletedApplicationLetter;
import com.wanted.wantedlab.entity.JobPost;
import com.wanted.wantedlab.dto.jobPost.request.JobPostDeleteRequest;
import com.wanted.wantedlab.dto.jobPost.request.JobPostUploadRequest;
import com.wanted.wantedlab.dto.jobPost.response.*;
import com.wanted.wantedlab.dto.jobPost.request.JobPostUpdateRequest;
import com.wanted.wantedlab.repository.ApplicationLetterRepository;
import com.wanted.wantedlab.repository.CompanyRepository;
import com.wanted.wantedlab.repository.DeletedApplicationLetterRepository;
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
  private final EntityValidator entityValidator;
  private final JobPostRepository jobPostRepository;
  private final ApplicationLetterRepository applicationLetterRepository;
  private final DeletedApplicationLetterRepository deletedApplicationLetterRepository;
  @Transactional
  public JobPostUploadResult upload(JobPostUploadRequest postUploadRequest){
    Company uploadCompany = entityValidator.validateCompany(postUploadRequest.getCompanyId());
    JobPost newJobPost = JobPost.createJobPost(postUploadRequest,uploadCompany);

    JobPost createdJobPost = jobPostRepository.save(newJobPost);
    return JobPostUploadResult.of(createdJobPost);
  }
  @Transactional
  public JobPostDeleteResult delete(JobPostDeleteRequest deleteRequest) {
    JobPost jobPost = entityValidator.validateJobPost(deleteRequest.getJobPostId());
    deleteApplicationLetters(jobPost.getId());
    jobPostRepository.delete(jobPost);
    return new JobPostDeleteResult(true);
  }
  private void deleteApplicationLetters(Long jobPostId){
    List<ApplicationLetter> letters = applicationLetterRepository.findDeleteApplicationLetter(jobPostId);
    deleteLetters(letters);
    createDeletedLetters(letters);
  }
  private void createDeletedLetters(List<ApplicationLetter> letters){
    List<DeletedApplicationLetter> deletedLetters = letters.stream().map(DeletedApplicationLetter::of).toList();
    deletedApplicationLetterRepository.saveAll(deletedLetters);
  }
  private void deleteLetters(List<ApplicationLetter> letters){
    List<Long> letterIds = letters.stream().map(ApplicationLetter::getId).toList();
    applicationLetterRepository.deleteLetterAssociatedJobPost(letterIds);
  }
  @Transactional
  public JobPostUpdateResult update(JobPostUpdateRequest updateRequest){
    JobPost jobPost = entityValidator.validateJobPost(updateRequest.getId());
    jobPost.update(updateRequest);
    return JobPostUpdateResult.of(jobPost);
  }

  public JobPostInfoList getJobPosts(int page,int size){
    PageRequest pageRequest = PageRequest.of(page,size);
    Slice<JobPost> jobPostSlice = jobPostRepository.getJobPostSlice(pageRequest);
    return JobPostInfoList.of(jobPostSlice);
  }
  public JobPostDetailInfo getJobPostDetail(Long jobPostId){
    JobPost jobPost = entityValidator.validateJobPost(jobPostId);
    PageRequest pageRequest = PageRequest.of(0,5);
    List<JobPost> companyJobPosts = jobPostRepository.getJobPostsByCompanyId(pageRequest,jobPost.getCompany().getId());
    return JobPostDetailInfo.of(jobPost,companyJobPosts);
  }
}
