package com.wanted.wantedlab.service;

import com.wanted.wantedlab.dto.applicationLetter.request.ApplyRequest;
import com.wanted.wantedlab.dto.applicationLetter.response.ApplyResult;
import com.wanted.wantedlab.entity.ApplicationLetter;
import com.wanted.wantedlab.entity.JobPost;
import com.wanted.wantedlab.entity.User;
import com.wanted.wantedlab.repository.ApplicationLetterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplicationLetterService {
  private final EntityValidator entityValidator;
  private final ApplicationLetterRepository applicationLetterRepository;
  @Transactional
  public ApplyResult apply(ApplyRequest applyRequest){
    User user = entityValidator.validateUser(applyRequest.getUserId());
    JobPost jobPost = entityValidator.validateJobPost(applyRequest.getJobPostId());
    ApplicationLetter letter = ApplicationLetter.createApplicationLetter(applyRequest,user,jobPost);

    ApplicationLetter savedLetter = applicationLetterRepository.save(letter);
    return ApplyResult.of(savedLetter);
  }
}
