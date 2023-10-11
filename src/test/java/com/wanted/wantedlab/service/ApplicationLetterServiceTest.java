package com.wanted.wantedlab.service;

import com.wanted.wantedlab.dto.applicationLetter.request.ApplyRequest;
import com.wanted.wantedlab.dto.applicationLetter.response.ApplyResult;
import com.wanted.wantedlab.entity.ApplicationLetter;
import com.wanted.wantedlab.entity.Company;
import com.wanted.wantedlab.entity.JobPost;
import com.wanted.wantedlab.entity.User;
import com.wanted.wantedlab.repository.ApplicationLetterRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplicationLetterServiceTest {
  @InjectMocks
  ApplicationLetterService applicationLetterService;
  @Mock
  EntityValidator entityValidator;
  @Mock
  ApplicationLetterRepository applicationLetterRepository;
  @Test
  @DisplayName("apply 성공 테스트")
  void apply_success(){
    //given
    ApplyRequest request = new ApplyRequest("sample-id",1L,"sample-portfolio");
    User sampleUser = new User("sample-id","sample-nickname");
    Company sampleCompany = new Company(1L,"sample-company","sample-country","sample-region");
    JobPost sampleJobPost = new JobPost(1L,"sample-position","sample-content",
            "sample-skills",100000,sampleCompany);
    ApplicationLetter sampleLetter = new ApplicationLetter(1L,sampleJobPost,sampleUser,
            "sample-portfolio");
    when(entityValidator.validateUser(anyString())).thenReturn(sampleUser);
    when(entityValidator.validateJobPost(anyLong())).thenReturn(sampleJobPost);
    when(applicationLetterRepository.save(any())).thenReturn(sampleLetter);

    ApplyResult expectedResult = new ApplyResult(1L,1L,"sample-id");

    //when
    ApplyResult result = applicationLetterService.apply(request);

    //then
    assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    verify(entityValidator,times(1)).validateUser(anyString());
    verify(entityValidator,times(1)).validateJobPost(anyLong());
    verify(applicationLetterRepository,times(1)).save(any());
  }
}
