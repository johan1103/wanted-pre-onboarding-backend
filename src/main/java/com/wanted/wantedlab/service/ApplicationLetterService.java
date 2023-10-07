package com.wanted.wantedlab.service;

import com.wanted.wantedlab.dto.applicationLetter.response.ApplyResult;
import com.wanted.wantedlab.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplicationLetterService {
  private final EntityValidator entityValidator;
  @Transactional
  public ApplyResult apply(String userId,String jobPostId){
    
    return null;
  }
}
